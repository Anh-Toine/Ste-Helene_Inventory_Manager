package com.depanneur_ste_helene.inventory_system.presentationlayer;

import com.depanneur_ste_helene.inventory_system.businesslayer.category.CategoryService;
import com.depanneur_ste_helene.inventory_system.datalayer.category.CategoryCreateDTO;
import com.depanneur_ste_helene.inventory_system.datalayer.category.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryRESTController {
    @Autowired
    private final CategoryService SERVICE;

    public CategoryRESTController(CategoryService SERVICE) {
        this.SERVICE = SERVICE;
    }

    @CrossOrigin
    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategory(){
        return SERVICE.getAllCategory();
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryCreateDTO newCategory){return SERVICE.createCategory(newCategory);}

    @CrossOrigin
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/categories/{categoryId}")
    public CategoryDTO updateCategory(@RequestBody CategoryDTO category, @PathVariable("categoryId") String categoryId){
        category.setCategoryId(categoryId);
        return SERVICE.updateCategory(category);
    }

    @CrossOrigin
    @DeleteMapping(path = "/categories/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId){SERVICE.deleteCategory(categoryId);}

}
