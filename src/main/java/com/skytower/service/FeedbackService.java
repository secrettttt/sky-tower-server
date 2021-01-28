package com.skytower.service;

public interface FeedbackService {

    int reportFeedback(String user_id, String feedback_rate, String report_content,
                        long create_time);
}
