package org.cloud.xue.controller;

import org.cloud.xue.pojo.User;
import org.cloud.xue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月14日 10:41:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
