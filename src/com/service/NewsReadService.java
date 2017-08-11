package com.service;

import java.util.Map;

public interface NewsReadService {
	/**
	 * 获取最近阅读
	 * @param uid
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNewsRead(String uid, int pageNo) throws Exception;

}