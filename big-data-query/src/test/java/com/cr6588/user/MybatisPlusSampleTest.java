package com.cr6588.user;



import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.cr6588.dao.UserMapper;
import com.cr6588.entity.User;

/**
 * create in 2022年04月02日
 * @category TODO
 * @author chenyi
 */
@MybatisPlusTest
public class MybatisPlusSampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
    }
}
