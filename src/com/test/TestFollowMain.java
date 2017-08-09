package com.test;

import com.alibaba.fastjson.JSON;
import com.service.CommentService;
import com.service.FollowAnchorService;
import com.service.UserInfoService;
import com.service.impl.CommentServiceImpl;
import com.service.impl.FollowAnchorServiceImpl;
import com.service.impl.UserInfoServiceImpl;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestFollowMain {
	private static FollowAnchorService followAnchorService = new FollowAnchorServiceImpl();
// 	private static IRedisDao redisFollowDao = DaoFactory.getFollowRedisDao();

    private static UserInfoService userInfoService = new UserInfoServiceImpl();

    private static CommentService commentService = new CommentServiceImpl();

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
        System.out.println(time/1000);
    }

    //查询评论回复
    @Test
    public void test3 () {
        try {
            List<Map<String, Object>> info = commentService.findCommentList("info", 4, 0, 0);
            System.out.println("-----------"+JSON.toJSONString(info));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加评论回复
    @Test
    public void test4 () {
        try {
            commentService.addComment("info", 4, 1, 1,"评论--测试啦啦啦-----------");
//            commentService.addComment("info", 3, 1, 76,"评论回复-----");
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
}
