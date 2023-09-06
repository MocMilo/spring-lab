package com.sandystack.exp.rest.redis;

import com.sandystack.exp.model.redis.Product;
import com.sandystack.exp.repository.redis.ProductRedisRepository;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/redis/product")
public class ExampleRedisDBController {

    private ProductRedisRepository dao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable String id) {
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable String id) {
        dao.deleteProduct(id);
        return "product deleted";
    }
}
