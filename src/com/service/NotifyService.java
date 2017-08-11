package com.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-11 0011.
 */
public interface NotifyService {
    Map<String,Object> getUnread(long userId);

    List<Map<String, Object>> getList(long userId, int type, int pageNo) throws Exception;
}
