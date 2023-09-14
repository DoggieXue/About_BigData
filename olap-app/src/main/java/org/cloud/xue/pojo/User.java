package org.cloud.xue.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @ClassName User
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:23:50
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Date create_time;
    private Date update_time;
}
