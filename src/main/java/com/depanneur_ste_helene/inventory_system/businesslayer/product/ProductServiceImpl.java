package com.depanneur_ste_helene.inventory_system.businesslayer.product;

import com.depanneur_ste_helene.inventory_system.datalayer.product.Product;
import com.depanneur_ste_helene.inventory_system.datalayer.product.ProductDTO;
import com.depanneur_ste_helene.inventory_system.datalayer.product.ProductRepository;
import com.depanneur_ste_helene.inventory_system.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;

import com.depanneur_ste_helene.inventory_system.exceptions.InvalidInputException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper){
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDTO createProduct(ProductDTO model) {

        if(productRepository.existsByBarCode(model.getBarCode())){
            throw new AlreadyExistsException("Product of the same barcode already exists");
        }

        if(model.getPrice() < 0 || model.getQuantity() < 0 || model.getQuantitySold() < 0){
            throw new InvalidInputException("Input not valid");
        }

        Product entity = mapper.modelToEntity(model);
        Product newEntity = productRepository.save(entity);

        return mapper.entityToModel(newEntity);
    }


    public ProductDTO updateProduct(ProductDTO model) {
        Product productEntity = mapper.modelToEntity(model);
        Optional<Product> returnedEntity = productRepository.findByBarCode(model.getBarCode());

        productEntity.setProductId(returnedEntity.get().getProductId());

        Product updatedProduct = productRepository.save(productEntity);
        return mapper.entityToModel(updatedProduct);
    }

    @Override
    public void deleteProduct(String barCode) {
        productRepository.findByBarCode(barCode).ifPresent(p -> productRepository.delete(p));
    }

    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> models = mapper.entityListToModelList(products);
        return models;
    }
}
