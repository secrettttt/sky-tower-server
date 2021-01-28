package com.skytower.dao;

import com.skytower.entry.FeedbackEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FeedbackMapper {
    @Insert({"insert into feedback_table(user_id, feedback_rate, feedback_content, feedback_time) " +
            "values('${e.user_id}', '${e.feedback_rate}', '${e.feedback_content}', '${e.feedback_time}')"})
    int reportFeedback(@Param("e") FeedbackEntry e);
}
