package com.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 云瑞 on 2017/8/8.
 */
public class MongodbUtils {
    static String dbname;
    static String table;
    static Long accentId;
    static Map<String,Object> record;

    static DBCollection collection = null;
    static Properties props;
    static InputStream is = null;

    public static void info () {
        try {
            props = new Properties();
            is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("localExtend.properties");
            props.load(is);

            String mongoHost = props.getProperty("dingzhi.mongoHost");

            String[] split = mongoHost.split(":");

            Mongo mongo = mongo = new Mongo(split[0], Integer.parseInt(split[1]));
            // 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
            DB db = mongo.getDB(dbname);
            // Get collection from MongoDB, database named "yourDB"
            // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
            collection = db.getCollection(table);
            // 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public static void insertDocumentAddList (Long accentId, Map<String,Object> record) {
        info ();
        BasicDBObject document = new BasicDBObject();
        document.put("id",accentId);

        BasicDBObject obj = new BasicDBObject();
        obj.put("replyList",record);

        BasicDBObject git = new BasicDBObject();
        git.put("$push",obj);

        System.out.println(git);
        collection.update(document,git);
    }

    public static String getDbname() {
        return dbname;
    }

    public static void setDbname(String dbname) {
        MongodbUtils.dbname = dbname;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        MongodbUtils.table = table;
    }

    public static Long getAccentId() {
        return accentId;
    }

    public static void setAccentId(Long accentId) {
        MongodbUtils.accentId = accentId;
    }

    public static Map<String, Object> getRecord() {
        return record;
    }

    public static void setRecord(Map<String, Object> record) {
        MongodbUtils.record = record;
    }

    public MongodbUtils(String dbname, String table,Long accentId,Map<String,Object> record) {
        this.dbname = dbname;
        this.table = table;
        this.accentId = accentId;
        this.record = record;
    }
}
