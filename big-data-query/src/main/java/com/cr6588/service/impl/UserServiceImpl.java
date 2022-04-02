package com.cr6588.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cr6588.dao.UserDao;
import com.cr6588.entity.User;
import com.cr6588.service.UserService;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList(Map<String, Object> param) {
        return userDao.getUserList(param);
    }

    @Override
    public User getUser(Map<String, Object> param) {
        return userDao.getUser(param);
    }

    @Override
    public User getUser(User user) {
        Map<String, Object> param = user.getParamMap();
        return userDao.getUser(param);
    }

    @Override
    public Long saveUser(User user) {
        userDao.saveUser(user);
        return user.getId();
    }

    @Override
    public void batchSaveUser(List<User> list) {
        userDao.batchSaveUser(list);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUserNoNull(User user) {
        userDao.updateUserNoNull(user);
    }

    @Override
    public void deleteUser(Map<String, Object> param) {
        userDao.deleteUser(param);
    }
}
