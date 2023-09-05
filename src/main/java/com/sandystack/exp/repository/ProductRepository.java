package com.sandystack.exp.repository;

import com.sandystack.exp.model.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

}
