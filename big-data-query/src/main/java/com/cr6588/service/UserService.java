package com.cr6588.service;

import java.util.List;
import java.util.Map;

import com.cr6588.entity.User;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
public interface UserService {
    /**
     * 查询用户列表
     * @param param 查询条件
     * @param pager 分页条件，无需分页null
     * @return
     */
    List<User> getUserList(Map<String, Object> param);

    /**
     * 查询用户
     * @param param 查询条件
     * @return
     */
    User getUser(Map<String, Object> param);

    /**
     * 查询用户
     * @param param 查询条件
     * @return
     */
    User getUser(User user);

    /**
     * 增加用户
     * @param user 用户
     * @return
     */
    Long saveUser(User user);

    /**
     * 批量增加用户
     * @param user 用户
     * @return
     */
    void batchSaveUser(List<User> list);

    /**
     * 修改用户
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 修改用户，非空字段
     * @param user
     */
    void updateUserNoNull(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Map<String, Object> param);
}
