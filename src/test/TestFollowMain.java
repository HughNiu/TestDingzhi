package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.service.CommentService;
import com.service.FollowAnchorService;
import com.service.PostBarService;
import com.service.UserInfoService;
import com.service.impl.CommentServiceImpl;
import com.service.impl.FollowAnchorServiceImpl;
import com.service.impl.PostBarServiceImpl;
import com.service.impl.UserInfoServiceImpl;
import com.util.DateStampConversion;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

public class TestFollowMain {
	private static FollowAnchorService followAnchorService = new FollowAnchorServiceImpl();
// 	private static IRedisDao redisFollowDao = DaoFactory.getFollowRedisDao();

    private static UserInfoService userInfoService = new UserInfoServiceImpl();

    private static CommentService commentService = new CommentServiceImpl();

    private static PostBarService postBarService = new PostBarServiceImpl();

 	public static void main(String[] args) {
		try {
			followAnchorService.addFollow(2,200);
		List<Map<String, Object>> lst=followAnchorService.queryFans(200,1, 0);

		System.out.println("-----"+JSON.toJSONString(lst));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1 () {
        try {
            Map<String, Object> userInfoById = userInfoService.getUserInfoById(8);
            System.out.println("-----------"+JSON.toJSONString(userInfoById));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2 () throws ParseException {
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        System.out.println(time);
    }

    //查询评论回复
    @Test
    public void test3 () {
        try {
            Map<String, Object> info = commentService.findCommentList("info", 4, 0, 0);
            System.out.println("-----------"+JSON.toJSONString(info));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加评论回复
    @Test
    public void test4 () {
        try {
//            commentService.addComment("info", 4, 1, 2,"评论--测试AAAAAAAA-----------");
            commentService.addComment("info", 3, 1, 80,"评论回复aaaaaaaaa-----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除评论回复
    @Test
    public void test5 () {
        try {
//            commentService.deleteComment("info",10);
            commentService.deleteReply("info",11,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //动态/文章/帖子/公告查询
    @Test
    public void test6 () {
        try {
            Map<String, Object> info = postBarService.getPostBarByType(1, "info", 0, 0);
            System.out.println("-----------"+JSON.toJSONString(info));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7 () {
        int type = 1;
        try {
            Map<String,Object> map = new HashedMap();
            map.put("userId","100");
            map.put("appId","info");
            map.put("type",type);
            if (type == 1) {
                map.put("isHot",1);
                map.put("title","db帖子33");
                map.put("content","db帖子33+++++++");
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
            }else if (type == 2) {
                map.put("content","db动态22");
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
                map.put("isHot",0);
            }else if (type == 3) {
                map.put("coverImg","http://封面33");
                map.put("title","db文章33");
                map.put("content","db文章33+++++++");
                map.put("commentImg",new String[]{"htpp://XXXXX1","htpp://XXXXX2","htpp://XXXXX2"});
                map.put("isHot",0);
            }else if (type == 4) {
                map.put("title","db公告33");
                map.put("startTime",new Date().getTime());
                map.put("endTime",Long.parseLong(DateStampConversion.dateToStamp(DateStampConversion.YYYY_MM_DD_HH_MM_SS,"2017-08-20 08:08:00")));
                map.put("isHot",0);
                map.put("jumpUrl","http://XXXXX");
            }
            postBarService.addPostBar(map);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void test8 () {
        String s = DateStampConversion.stampToDate(DateStampConversion.YYYY_MM_DD_HH_MM_SS, "1502278431362");
        System.out.println(s);
        try {
            String time = DateStampConversion.getTime("1502278431362");
            System.out.println(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
