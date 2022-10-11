package com.crm.settings.service;

import com.crm.settings.model.User;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserService
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/13 0:31
 * @Author:wang ranheng
 */
public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
    List<User> queryAllUsers();
}
