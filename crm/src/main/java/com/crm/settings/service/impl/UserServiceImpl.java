package com.crm.settings.service.impl;

import com.crm.settings.mapper.UserMapper;
import com.crm.settings.model.User;
import com.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserServiceImpl
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/13 0:33
 * @Author:wang ranheng
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
