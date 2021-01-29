package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectEntry {
    private int project_id;
    private String project_name;
    private String description;
    private String url_online;
    private boolean is_monitoring;
    private long create_time;
    private String user_id;
}
