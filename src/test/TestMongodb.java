package test;

import com.dao.DaoFactory;
import com.mongodb.BasicDBObject;
import com.zw.zcf.dao.mongo.IMongoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMongodb {

//	public static void main(String[] args) {
//		for(int i=8;i<=163;i++){
//			String dString="db.anchor.save({\"anchorId\" : NumberLong("+i+"), \"updateTime\" : NumberLong(\""+System.currentTimeMillis()+"\"), \"status\" : 0, \"openLiveTime\" : NumberLong(\"1495622783474\"), \"sevenMoney\" : 0});";
//
//			System.out.println(dString);
//		}
//
//	}

	public   static IMongoDao mongoDao;
	private   int pageSize=10;

	/**
	 * 添加关注
	 */
	public static void add(long uid,long anchorId) throws Exception {
		Map<String,Object> record=new HashMap<String,Object>();
		record.put("unitId", 1);

		List list = new ArrayList();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "西门");
		list.add(map);
		record.put("list", list);

		mongoDao.insert(record);
	}
	public static void addToSet() throws Exception {
		Map<String,Object> cond=new HashMap<String,Object>();
		cond.put("unitId", 1);


		List list = new ArrayList();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "吹雪5");

		Map<String,Object> mapColKey=new HashMap<String,Object>();
		mapColKey.put("list",map);

		Map<String,Object> record=new HashMap<String,Object>();
		record.put("$addToSet", mapColKey);

		mongoDao.getDb().getCollection("test").findAndModify(new BasicDBObject(cond),
				new BasicDBObject(record));
	}
	public static void main(String[] args) throws Exception {
		mongoDao = DaoFactory.getFollowMongoDao
				("dingzhidb","test");
//				mongoDao.clear(true);
//		add(1L,1L);
//		System.out.println(mongoDao.findAll());
		addToSet();
		System.out.println(mongoDao.findAll());

//		mongoDao.unset(null,null,false,false);


	}


}
