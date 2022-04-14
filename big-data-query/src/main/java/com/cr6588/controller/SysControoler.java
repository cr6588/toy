package com.cr6588.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.vo.ApiRes;

/**
 * create in 2022年04月01日
 * @category TODO
 * @author chenyi
 */
@RestController
@RequestMapping("/api")
public class SysControoler {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiRes<String> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public ApiRes<String> register(@RequestBody User user) {
        return userService.register(user);
    }


    
}
