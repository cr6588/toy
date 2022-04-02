package com.cr6588.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create in 2022年03月31日
 * @category TODO
 * @author chenyi
 */
@Controller
public class ViewController {

    @RequestMapping("/register")
    public String register() {
        return "register.html";
    }


    @RequestMapping("/userList")
    public String userList() {
        return "userList.html";
    }
}
