package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectViewEntry {
    private int record_id;
    private String project_id;
    private String uid;
    private long view_time;
}
