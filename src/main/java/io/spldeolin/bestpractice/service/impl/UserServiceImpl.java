package io.spldeolin.bestpractice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.spldeolin.bestpractice.mapper.UserMapper;
import io.spldeolin.bestpractice.po.UserPo;
import io.spldeolin.bestpractice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void batchCreate() throws Exception {
        userMapper.insert(new Date(100000));
        Integer.valueOf("a");
        userMapper.insert(new Date());
    }

    @Override
    public List<UserPo> getBatch() throws Exception {
        PageHelper.startPage(3, 2);
        List<UserPo> userPos = userMapper.list();
        PageInfo<UserPo> userPage = new PageInfo<>(userPos);
        // 获得应该在当前页显示的数据的List
        List<UserPo> posInPage = userPage.getList();
        return posInPage;
    }

    // @Cacheable(value = "users")
    @Override
    public List<UserPo> getUserByNickname(String nickname) {
        return userMapper.getUserByNickname(nickname);
    }

    @Cacheable(value = "users", key = "caches[0].name")
    @Override
    public List<UserPo> getUserByPassword(String password) {
        return userMapper.getUserByPassword(password);
    }

    @CachePut(value = "users", key = "caches[0].name")
    @Override
    public List<UserPo> createUser(String nickname, String password) {
        // Map<String, Object> map = new HashMap<>();
        // map.put("insert_time", new Date());
        // map.put("nickname", nickname);
        // map.put("password", password);
        // userMapper.insertUser(map);
        List<UserPo> userPo = new ArrayList<>();
        UserPo po = new UserPo();
        po.setId(999);
        userPo.add(po);
        return userPo;
    }

}
