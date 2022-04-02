package com.cr6588.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.util.RandomUtil;

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
    public String login(@RequestBody User user) {
        if(!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            return "please input username or paassword";
        }
        User u = userService.getUser(user);
        if(u == null) {
            return "err";
        }
        return "succ";
    }


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String numStr = RandomUtil.getNumStr(13);
        user.setId(Long.parseLong(numStr));
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("username", user.getUsername());
        List<User> users = userService.getUserList(param);
        if(!CollectionUtils.isEmpty(users)) {
            return "用户已存在";
        }
        userService.saveUser(user);
        return "succ";
    }
}
