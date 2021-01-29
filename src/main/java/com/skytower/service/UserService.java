package com.skytower.service;

import com.skytower.entry.UserEntry;

import java.util.List;

public interface UserService {

    List<UserEntry> checkPermission(String username, String password);

    boolean isUserExist(String user_id);

    int createNewUser(String username, String password, String email,
                      String phone_number, long user_create_time);

    int updateUserInfo(String user_id, String username, String password,
                   String email, String phone_number);

    List<UserEntry> getUserInfo(String user_id);

    boolean isUserNameExist(String username);
}