package com.service;

import java.util.List;
import java.util.Map;

public interface ChapterPaylogService {

	/**
	 * 判断用户是否购买章节
	 * @param bookId
	 * @param charpterId
	 * @return
	 * @throws Exception
	 */
		public Boolean ChapterPaylogBuy(String userId,String bookId, String charpterId) throws Exception;


}