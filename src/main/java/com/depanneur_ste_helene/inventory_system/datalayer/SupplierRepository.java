package com.depanneur_ste_helene.inventory_system.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Transactional(readOnly = true)
    Optional<Supplier> findSupplierBySupplierName(String name);

    @Transactional(readOnly = true)
    List<Supplier> findBySupplierId(Integer supplierId);
}