package org.cloud.xue;

import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @ClassName OLAPApplication
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:07:18
 * @Version 1.0
 **/
//exclude属性值为SecurityAutoConfiguration.class，则关闭Spring Security校验
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

@SpringBootApplication
public class OLAPApplication {
    public static void main(String[] args) {
        SpringApplication.run(OLAPApplication.class, args);
    }
}
