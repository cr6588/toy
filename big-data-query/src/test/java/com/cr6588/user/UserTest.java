package com.cr6588.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        for(int i = 0; i <1000000; i++) {
            String numStr = RandomUtil.getNumStr(13);
            User user = new User();
            user.setId(Long.parseLong(numStr));
            user.setUsername(RandomUtil.getStr((i % 10) + 1));
            user.setName(RandomUtil.getChineseStr((i % 3) + 1));
            user.setPassword(RandomUtil.getStr((i % 10) + 1));
            userList.add(user);
            try {
                if(userList.size() == 10000) {
                    userService.batchSaveUser(userList);
                    userList.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if(userList.size() != 0) {
                userService.batchSaveUser(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testName() throws Exception {
        User user = new User();
        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
    }
}
