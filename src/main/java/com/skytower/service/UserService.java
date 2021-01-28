package com.skytower.service;

import com.skytower.entry.UserEntry;

import java.util.List;

public interface UserService {

    List<UserEntry> checkPermission(String username, String password);

    boolean isUserExist(String user_id);
}