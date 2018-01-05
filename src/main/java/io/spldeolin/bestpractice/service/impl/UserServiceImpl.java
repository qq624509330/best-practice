package io.spldeolin.bestpractice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.spldeolin.bestpractice.dao.UserMapper;
import io.spldeolin.bestpractice.po.User;
import io.spldeolin.bestpractice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void batchCreate() throws Exception {
        User user1 = new User();
        user1.setUsername("发生异常前插入");
        userMapper.insert(user1);
        User user2 = new User();
        user2.setUsername("发生异常后插入");

        Integer.valueOf("a");

        userMapper.insert(user2);

    }

    @Override
    public List<User> getBatch() throws Exception {
        return userMapper.selectAll();
    }

}
