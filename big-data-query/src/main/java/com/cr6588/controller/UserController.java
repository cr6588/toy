package com.cr6588.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.util.RandomUtil;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUserList(User user) {
        return userService.getUserList(user.getParamMap());
    }

    @PostMapping
    public String save(@RequestBody User user) {
        String numStr = RandomUtil.getNumStr(13);
        user.setId(Long.parseLong(numStr));
        userService.saveUser(user);
        return "succ";
    }

    @DeleteMapping
    public String delete(@RequestParam("ids")List<Long> ids) {
        if(ids == null || ids.size() == 0) {
            return "succ";
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ids", ids);
        userService.deleteUser(param);
        return "succ";
    }
}
