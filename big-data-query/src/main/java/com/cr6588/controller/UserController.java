package com.cr6588.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cr6588.dao.UserMapper;
import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.util.RandomUtil;
import com.cr6588.vo.ApiRes;
import com.cr6588.vo.Pager;

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
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public ApiRes<List<User>> getUserList(User user, Pager page) {
        IPage<User> res = userService.getUserList(page, user);
        Pager resPage = new Pager(res.getTotal(), res.getSize(), res.getCurrent());
        return ApiRes.succ(res.getRecords(), resPage);
    }

    @PostMapping
    public String save(@RequestBody User user) {
        String numStr = RandomUtil.getNumStr(13);
        user.setId(Long.parseLong(numStr));
        userMapper.insert(user);
        return "succ";
    }

    @DeleteMapping
    public ApiRes<String> delete(@RequestParam("ids")List<Long> ids) {
        if(ids == null || ids.size() == 0) {
            return ApiRes.err("id is null");
        }
        userMapper.deleteBatchIds(ids);
        return ApiRes.emptySucc();
    }
}
