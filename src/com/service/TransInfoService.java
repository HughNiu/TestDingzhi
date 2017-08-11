package com.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-10 0010.
 */
public interface TransInfoService {
    List<Map<String, Object>> getTransList(long userId, List<Integer> types, int pageNo);
}
