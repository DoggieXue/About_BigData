package org.cloud.xue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Product
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:25:48
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private Long price;
    private String version;
}
