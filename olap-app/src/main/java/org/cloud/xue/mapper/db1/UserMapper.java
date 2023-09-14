package org.cloud.xue.mapper.db1;

import org.apache.ibatis.annotations.Mapper;
import org.cloud.xue.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:19:12
 * @Version 1.0
 **/
@Mapper
@Repository
public interface UserMapper {
    List<User> getAllUsers();
    User getUserById(Long id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
