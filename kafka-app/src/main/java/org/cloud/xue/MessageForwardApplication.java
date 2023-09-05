package org.cloud.xue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @ClassName MessageForwardApplication
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月01日 14:42:28
 * @Version 1.0
 **/
@Slf4j
@SpringBootApplication
public class MessageForwardApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageForwardApplication.class, args);
        log.info("应用启动成功，当前时间戳：{}", System.currentTimeMillis());
    }
}
