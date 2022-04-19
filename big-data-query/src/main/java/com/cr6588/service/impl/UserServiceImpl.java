package com.cr6588.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cr6588.dao.UserMapper;
import com.cr6588.entity.User;
import com.cr6588.service.UserService;
import com.cr6588.vo.ApiRes;
import com.cr6588.vo.Pager;

/**
 * create in 2022年03月30日
 * @category TODO
 * @author chenyi
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ApiRes<String> register(User user) {
        Pager page = new Pager(1, 1);
        User param = new User();
        param.setUsername(user.getUsername());
        IPage<User> res = getUserList(page, param);
        if (!CollectionUtils.isEmpty(res.getRecords())) {
            return ApiRes.err("用户已存在");
        }
        baseMapper.insert(user);
        return ApiRes.emptySucc();
    }

    @Override
    public IPage<User> getUserList(Pager pager, User user) {
        IPage<User> page = new Page<User>(pager.getCurrent(), pager.getSize(), pager.isSearchCount());
        if(user == null) {
            user = new User();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<User>(user);
        //与like结果不一样
//        wrapper.apply(StringUtils.hasText(user.getUsernameLike()), "match(username) against(+{0} in boolean mode)", user.getUsernameLike());
//        wrapper.apply(StringUtils.hasText(user.getNameLike()), "match(name) against(+{0} in boolean mode)", user.getNameLike());
        wrapper.like(StringUtils.hasText(user.getUsernameLike()), "username", user.getUsernameLike());
        wrapper.like(StringUtils.hasText(user.getNameLike()), "name", user.getNameLike());
        wrapper.select("id");

        IPage<User> res = baseMapper.selectPage(page, wrapper);
        List<User> records = res.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return res;
        }
        long total = res.getTotal();
        List<Long> ids = records.stream().map(r -> r.getId()).collect(Collectors.toList());
        records = baseMapper.selectBatchIds(ids);
        res.setRecords(records);
        res.setTotal(total);
        return res;
    }

    @Override
    public ApiRes<String> login(User user) {
        if (!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            return ApiRes.err("please input username or paassword");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<User>(user);
        User u = baseMapper.selectOne(wrapper);
        if (u == null) {
            return ApiRes.err("username or password error");
        }
        return ApiRes.emptySucc();
    }
}
