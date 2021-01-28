package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackEntry {
    private int feedback_id;
    private String user_id;
    private String feedback_rate;
    private String feedback_content;
    private Long feedback_time;
}
