package com.cr6588.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cr6588.entity.User;
import com.cr6588.vo.ApiRes;
import com.cr6588.vo.Pager;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
public interface UserService extends IService<User> {

    ApiRes<String> login(User user);

    ApiRes<String> register(User user);

    IPage<User> getUserList(Pager pager, User user);
}
