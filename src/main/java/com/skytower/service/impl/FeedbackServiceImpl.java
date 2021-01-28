package com.skytower.service.impl;

import com.skytower.dao.FeedbackMapper;
import com.skytower.entry.FeedbackEntry;
import com.skytower.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired(required = false)
    protected FeedbackMapper feedbackMapper;

    @Override
    public int reportFeedback(
            String user_id,
            String feedback_rate,
            String report_content,
            long create_time
    ) {
        FeedbackEntry e = new FeedbackEntry();
        e.setUser_id(user_id);
        e.setFeedback_rate(feedback_rate);
        e.setFeedback_content(report_content);
        e.setFeedback_time(create_time);
        return feedbackMapper.reportFeedback(e);
    }
}
