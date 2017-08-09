package com.service;

import com.vo.Product;

/**
 * 新增产品类
 * Created by luozi on 2017/7/20.
 */
public interface ProductService {
    /**
     * 新增产品
     * @param product 产品类
     * @throws Exception
     */
    public boolean addProduct(Product product) throws Exception;
}
