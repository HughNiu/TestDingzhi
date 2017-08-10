package com.service.impl;

import com.common.Constants;
import com.dao.PostBarDao;
import com.dao.TransInfoDao;
import com.service.TransInfoService;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-10 0010.
 */
public class TransInfoServiceImpl implements TransInfoService {

    private TransInfoDao transInfoDao = new TransInfoDao();

    private PostBarDao postBarDao = new PostBarDao();

    public List<Map<String, Object>> getTransList(long userId, List<Integer> types, int pageNo, int pageSize) {
        List<Map<String, Object>> transList = transInfoDao.getByPage(userId, types, pageNo, pageSize);
        // 查询充值记录时返回无goodsName字段
        if (!types.contains(Constants.TRANS_RECHARGE)) {
            for (Map<String, Object> transInfo:transList) {
                long goodsId = MapUtils.getLongValue(transInfo, "goodsId", 0);
                int transType = MapUtils.getIntValue(transInfo, "type", -1);
                Map<String, Object> goodsInfo = getGoodsInfo(goodsId, transType);
                transInfo.put("goodsName", goodsInfo.get("goodsName"));
            }
        }
        return null;
    }

    private Map<String,Object> getGoodsInfo(long goodsId, int transType) {
        Map<String, Object> goodsInfo = new HashMap<String, Object>();
        switch (transType) {
            case Constants.TRANS_REWARD_ARTICLE:
                // 查询文章标题
                Map<String, Object> postBar = postBarDao.findPostBarById(goodsId);
                goodsInfo.put("goodsName", postBar.get("title"));
                break;
            case Constants.TRANS_REWARD_CHAPTER:
                // 查询图书标题
                break;
            case Constants.TRANS_SUBSCRIBE:
                // 查询图书标题
                break;
            case Constants.TRANS_GIFT:
                // 查询礼品名称
                break;
        }
        return null;
    }
}
