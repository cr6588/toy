package com.cr6588.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr6588.App;
import com.cr6588.dao.UserMapper;
import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.util.RandomUtil;

/**
 * create in 2022年04月01日
 * @category TODO
 * @author chenyi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void userServiceTest() {
        List<User> userList = new ArrayList<>();
        for(int i = 0; i <3000000; i++) {
            User user = new User();
            user.setUsername(RandomUtil.getStr((i % 100) + 1));
            user.setName(RandomUtil.getChineseStr((i % 100) + 1));
            user.setPassword(RandomUtil.getStr((i % 100) + 1));
            userList.add(user);
            try {
                if(userList.size() == 20000) {
                    userService.saveBatch(userList);
                    userList.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if(userList.size() != 0) {
                userService.saveBatch(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userMapperName() throws Exception {
        User user = new User();
        userMapper.insert(user);
        Long id = user.getId();
        Assert.assertNotNull(id);
        IPage<User> page = new Page<User>(1, 1);
        Wrapper<User> query = new LambdaQueryWrapper<>(user);
        IPage<User> res = userMapper.selectPage(page, query);
        Assert.assertEquals(res.getRecords().size(), 1);
        Assert.assertEquals(res.getTotal(), 1);
        userMapper.deleteBatchIds(Arrays.asList(id));
        Assert.assertNull(userMapper.selectById(id));
    }
}
