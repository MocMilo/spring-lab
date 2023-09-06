package com.sandystack.exp.rest.redis;

import com.sandystack.exp.model.redis.Product;
import com.sandystack.exp.repository.redis.ProductRedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * Product with price > 1000 is returned always form Database,
     * Product with price <= 1000 is first time returned from DB, later from cache
     */
    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product", unless = "null != #result && #result.price > 1000")
    public Product findProductById(@PathVariable String id) {
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")  // if deleted it will also be deleted form cache
    public String remove(@PathVariable String id) {
        dao.deleteProduct(id);
        return "product deleted";
    }
}
