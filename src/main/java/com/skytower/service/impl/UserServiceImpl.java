package com.skytower.service.impl;

import com.skytower.dao.ProjectMapper;
import com.skytower.dao.UserMapper;
import com.skytower.entry.ProjectEntry;
import com.skytower.entry.UserEntry;
import com.skytower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    protected UserMapper userMapper;

    @Autowired(required = false)
    protected ProjectMapper projectMapper;

    @Override
    public List<UserEntry> checkPermission(String username, String password) {
        UserEntry e = new UserEntry();
        e.setUsername(username);
        e.setPassword(password);
        return userMapper.checkPermission(e);
    }

    @Override
    public boolean isUserExist(String user_id) {
        List<UserEntry> res = userMapper.isUserExist(user_id);

        if(res.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int createNewUser(String username, String password, String email,
                      String phone_number, long user_create_time) {
        UserEntry e = new UserEntry();
        e.setUsername(username);
        e.setPassword(password);
        e.setEmail(email);
        e.setPhone_number(phone_number);
        e.setUser_create_time(user_create_time);
        return userMapper.createNewUser(e);
    }

    @Override
    public int updateUserInfo(String user_id, String username, String password,
                                  String email, String phone_number) {
        UserEntry e = new UserEntry();
        e.setUser_id(user_id);
        e.setUsername(username);
        e.setPassword(password);
        e.setEmail(email);
        e.setPhone_number(phone_number);
        return userMapper.updateUserInfo(e);
    }

    @Override
    public List<UserEntry> getUserInfo(String user_id) {
        return userMapper.getUserInfo(user_id);
    }

    @Override
    public boolean isUserNameExist(String username) {
        if (userMapper.getUserInfoByUsername(username).size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ProjectEntry> getUserList(String user_id) {
        return projectMapper.getUserList(user_id);
    }
}
