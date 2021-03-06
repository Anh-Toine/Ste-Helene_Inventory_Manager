package com.depanneur_ste_helene.inventory_system.businesslayer.category;

import com.depanneur_ste_helene.inventory_system.datalayer.category.Category;
import com.depanneur_ste_helene.inventory_system.datalayer.category.CategoryCreateDTO;
import com.depanneur_ste_helene.inventory_system.datalayer.category.CategoryDTO;
import com.depanneur_ste_helene.inventory_system.datalayer.category.CategoryRepository;
import com.depanneur_ste_helene.inventory_system.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> models = mapper.entityListToModelList(categories);
        return models;
    }

    public CategoryDTO createCategory(CategoryCreateDTO model) {
        if(categoryRepository.existsByCategoryName(model.getCategoryName())){
            throw new AlreadyExistsException("A category of the same name already exists");
        }

        Category entity = mapper.createDTOToEntity(model);
        Category newEntity = categoryRepository.save(entity);

        return mapper.entityToModel(newEntity);
    }

    public CategoryDTO updateCategory(CategoryDTO model){
        Category categoryEntity = mapper.modelToEntity(model);
        Optional<Category> returnedEntity = categoryRepository.findByCategoryId(model.getCategoryId());

        categoryEntity.setId(returnedEntity.get().getId());

        Category updateCategory = categoryRepository.save(categoryEntity);
        return mapper.entityToModel(updateCategory);
    }

    public void deleteCategory(String categoryId) {
        categoryRepository.findByCategoryId(categoryId).ifPresent(p -> categoryRepository.delete(p));
    }


}
