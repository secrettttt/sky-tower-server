package com.skytower.service.impl;

import com.skytower.dao.UserMapper;
import com.skytower.entry.UserEntry;
import com.skytower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    protected UserMapper userMapper;

    @Override
    public List<UserEntry> checkPermission(String username, String password) {
        UserEntry e = new UserEntry();
        e.setUsername(username);
        e.setPassword(password);
        return userMapper.checkPermission(e);
    }
}
