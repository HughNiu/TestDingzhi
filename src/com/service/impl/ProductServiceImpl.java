package com.service.impl;

import com.dao.DaoFactory;
import com.dao.ProductDao;
import com.service.ProductService;
import com.vo.Product;
import com.zw.zcf.dao.redis.IRedisDao;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Created by luozi on 2017/7/20.
 */
public class ProductServiceImpl implements ProductService {
    private static ProductDao productDao = new ProductDao();
    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
    private IRedisDao redisDao = DaoFactory.getRedisDao();

    /**
     * 新增产品
     *
     * @param product 产品类
     * @throws Exception
     */
    public boolean addProduct(Product product) throws Exception {
        boolean temp = false;
        if (product != null && StringUtils.isNotBlank(product.getAppid())) {
            productDao.addProduct(product);
            temp = true;
        }
        logger.info("-----新增产品 addProduct----" + product.toString());
        return temp;
    }
}
