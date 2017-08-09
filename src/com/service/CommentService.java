package com.service;

import java.util.List;
import java.util.Map;

/**
 * 评论回复
 * Created by 云瑞 on 2017/8/7.
 */
public interface CommentService {

    List<Map<String, Object>> findCommentList(String appId, int type, int pageNo, long timestamp) throws Exception;

    void addComment(String appId, int type, long userId, long accentId, String comtent) throws Exception;

    void deleteComment(String appId, int id);

    void deleteReply(String appId, int id, int commentId);
}
