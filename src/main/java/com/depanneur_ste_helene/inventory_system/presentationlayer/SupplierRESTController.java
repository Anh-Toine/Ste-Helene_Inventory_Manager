package com.depanneur_ste_helene.inventory_system.presentationlayer;

import com.depanneur_ste_helene.inventory_system.businesslayer.SupplierService;
import com.depanneur_ste_helene.inventory_system.datalayer.Supplier;
import com.depanneur_ste_helene.inventory_system.datalayer.SupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierRESTController {
    @Autowired
    private final SupplierService SERVICE;

    public SupplierRESTController(SupplierService SERVICE) {
        this.SERVICE = SERVICE;
    }

    @CrossOrigin
    @GetMapping("/suppliers")
    public List<SupplierDTO> getAllSuppliers(){
        return SERVICE.getAllSuppliers();
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE,
                path = "/suppliers")
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplier){
        return SERVICE.createSupplier(supplier);
    }
}