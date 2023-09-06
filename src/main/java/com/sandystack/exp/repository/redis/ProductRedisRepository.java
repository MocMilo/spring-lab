package com.sandystack.exp.repository.redis;

import com.sandystack.exp.model.redis.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Example Redis repository for Product entity
 */
@Repository
public class ProductRedisRepository {

    public static final String HASH_KEY = "Product";

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(String id) {
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(String id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product deleted";
    }
}
