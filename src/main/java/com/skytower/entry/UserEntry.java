package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntry {
    private String user_id;
    private String username;
    private String avatar;
    private String password;
    private String phone_number;
    private String email;
    private Long user_create_time;
}
