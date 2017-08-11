package com.service;

import java.util.List;
import java.util.Map;

public interface BookService {
	/**
	 * 获取首页书数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getBookData(String appId, int pageNo) throws Exception;

	/**
	 * 获取所有目录
	 * @param bookId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getBookChapter(String bookId,String userId) throws Exception;

	/**
	 * 根据图书id和章节id获取图书的内容
	 * @param bookId
	 * @param charpterId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getContentByCharpterId(String bookId,String charpterId) throws Exception;


}