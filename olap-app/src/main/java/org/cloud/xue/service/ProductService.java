package org.cloud.xue.service;

import org.cloud.xue.mapper.db2.ProductMapper;
import org.cloud.xue.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductService
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 11:22:47
 * @Version 1.0
 **/
@Service
public class ProductService {
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productMapper.getProductById(id);
    }

    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }
}
