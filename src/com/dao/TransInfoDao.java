package com.dao;

import com.vo.TransInfo;
import com.zw.zcf.dao.mongo.IMongoDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niu Qianghong on 2017-08-10 0010.
 */
public class TransInfoDao {
    private IMongoDao mongoDao;
    public TransInfoDao() {
        mongoDao = DaoFactory.getMongoDao("dingzhidb", "cust_trans_info");
    }

    /**
     * 添加transInfo记录
     * @param transInfo trans对象
     */
    public void insert(TransInfo transInfo) throws Exception {
        Map<String, Object> transInfoMap = transInfo.toMap();
        mongoDao.insert(transInfoMap);
    }

    /**
     * 添加transInfo记录, map中必须包含transInfo所有字段
     * @param transInfo
     */
    public void insert(Map<String, Object> transInfo) {
        mongoDao.insert(transInfo);
    }

    @Test
    public void test(){
        List<Integer> types = new ArrayList<Integer>();
        types.add(1);
        List<Map<String, Object>> byPage = getByPage(1, types, 0, 10);
        System.out.println(byPage);
    }

    /**
     * 分页获取交易信息
     * @param userId
     * @param types 交易类型
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    public List<Map<String, Object>> getByPage(long userId, List<Integer> types, int pageNo, int pageSize) {
        Map<String, Object> cond = new HashMap<String, Object>();
        List<Map<String, Object>> typeList = new ArrayList<Map<String, Object>>();
        for (int type:types) {
            Map<String, Object> tmp = new HashMap<String, Object>();
            tmp.put("type", type);
            typeList.add(tmp);
        }
        cond.put("$or", typeList);
        cond.put("userId", userId);

        Map<String, Object> order = new HashMap<String, Object>();
        order.put("timestamp", -1);

        pageNo = (pageNo <= 1 ? 0 : pageNo - 1);
        return mongoDao.findList(cond, order, pageNo * pageSize, pageSize);
    }
}
