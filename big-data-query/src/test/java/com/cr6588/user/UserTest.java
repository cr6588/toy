package com.cr6588.user;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr6588.App;
import com.cr6588.dao.UserMapper;
import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.util.RandomUtil;
import com.cr6588.vo.Pager;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * create in 2022年04月01日
 * @category TODO
 * @author chenyi
 */
@ExtendWith(SpringExtension.class)
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
        Assertions.assertNotNull(id);
        IPage<User> page = new Page<User>(1, 1);
        Wrapper<User> query = new LambdaQueryWrapper<>(user);
        IPage<User> res = userMapper.selectPage(page, query);
        Assertions.assertEquals(res.getRecords().size(), 1);
        Assertions.assertEquals(res.getTotal(), 1);
        userMapper.deleteBatchIds(Arrays.asList(id));
        Assertions.assertNull(userMapper.selectById(id));
    }

    @Test
    void addESDateByDb() throws Exception {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        int size = 30000;
        Pager page = new Pager(size, 1, false);
        for (int i = 170; ; i++) {
            page.setCurrent(i);
            IPage<User> res = userService.getUserList(page, null);
            List<User> users = res.getRecords();
            if(users == null) {
                break;
            }
            List<BulkOperation> list = new ArrayList<BulkOperation>();
            for (User user : users) {
                User temp = new User();
                temp.setUsername(user.getUsername());
                temp.setName(user.getName());
                //                CreateRequest<User> reqqq = CreateRequest.of(p -> p.index("user").document(temp).id(user.getId() + ""));
//                client.create(reqqq);
                CreateOperation<User> reqqq = CreateOperation.of(p -> p.index("user").document(temp).id(user.getId() + ""));
                BulkOperation bo = BulkOperation.of(b -> b.create(reqqq));
                list.add(bo);
            }
            BulkRequest bulk = BulkRequest.of(b -> b.index("user").operations(list));
            client.bulk(bulk);
            if(users.size() < 100) {
                break;
            }
        }
        
        
    }
}
