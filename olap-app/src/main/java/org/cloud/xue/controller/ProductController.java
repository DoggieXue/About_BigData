package org.cloud.xue.controller;

import org.cloud.xue.pojo.Product;
import org.cloud.xue.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ProductController
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 13:32:17
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void insertProduct(@RequestBody Product product) {
        productService.insertProduct(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
