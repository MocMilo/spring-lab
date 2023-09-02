package com.sandystack.exp.repository;

import com.sandystack.exp.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

}
