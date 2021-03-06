package com.depanneur_ste_helene.inventory_system.datalayer.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Integer>{

    @Transactional(readOnly = true)
    Optional<Category> findByCategoryId(String categoryId);

    @Transactional(readOnly = true)
    Optional<Category> findByCategoryName(String categoryName);
    @Transactional(readOnly = true)
    boolean existsByCategoryName(String categoryName);
}
