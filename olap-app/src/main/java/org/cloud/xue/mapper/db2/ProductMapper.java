package org.cloud.xue.mapper.db2;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.xue.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ProductMapper
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:23:19
 * @Version 1.0
 **/
@Mapper
@Repository
public interface ProductMapper {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
