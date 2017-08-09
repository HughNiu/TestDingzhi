package com.handler;

import com.service.ProductService;
import com.service.impl.ProductServiceImpl;
import com.vo.Product;
import com.zw.zcf.command.Command;
import com.zw.zcf.command.Response;
import com.zw.zcf.command.handler.MultiCommandHandler;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by luozi on 2017/7/20.
 */
public class ProductHandler extends MultiCommandHandler {
    private static Logger logger = Logger.getLogger(ProductHandler.class);
    static ProductService productService = new ProductServiceImpl();



    /**
     * 发布动态
     */
    public void publishDynamic (Command cmd) {
        try {
            logger.info("-------publishDynamic-------cmd:" + cmd);
            //公共参数 start
            String loginId=cmd.getStringParam("loginId");
            String appId=cmd.getStringParam("appId");
            String authorId=cmd.getStringParam("authorId");
            String version=cmd.getStringParam("version");
            String IMEI=cmd.getStringParam("IMEI");
            String model=cmd.getStringParam("model");
            String platform=cmd.getStringParam("platform");
            //公共参数 end
            String imgUrls="";//上传图片逻辑
            Product product = new Product();
            product.setAppid(appId);
            product.setImgUrls(imgUrls);
            product.setContent(cmd.getStringParam("content"));
            product.setPublishUserId(loginId);
//            product.setType(PRODUCTTYPE.DYNAMIC.getValue());
            product.setCreateDate(new Date().getTime());
            product.setCreateUserID(loginId);
            product.setLatestDate(new Date().getTime());
            boolean temp = productService.addProduct(product);
            Response response = new Response();
            if (temp == true) {
                response.addValue("code", "0");
                response.addValue("info", "SUCCESS");
                cmd.setResponse(response);
            } else {
                response.addValue("code", "1");
                response.addValue("info", "发布动态失败");
                cmd.setResponse(response);
            }

        } catch (Exception ex) {
            Response response = new Response();
            response.addValue("code", "1");
            response.addValue("info", "发布动态失败");
            cmd.setResponse(response);
            logger.error("publishDynamic----cmd:" + cmd + ",exception:", ex);
        }

    }
    /**
     * 发布公告
     */
    public void publishNotice (Command cmd) {
        try {
            logger.info("-------publishNotice-------cmd:" + cmd);
            //公共参数 start
            String loginId=cmd.getStringParam("loginId");
            String appId=cmd.getStringParam("appId");
            String authorId=cmd.getStringParam("authorId");
            String version=cmd.getStringParam("version");
            String IMEI=cmd.getStringParam("IMEI");
            String model=cmd.getStringParam("model");
            String platform=cmd.getStringParam("platform");
            //公共参数 end
            String imgUrls="";//上传图片逻辑
            Product product = new Product();
            product.setAppid(appId);
            product.setImgUrls(imgUrls);
            product.setContent(cmd.getStringParam("content"));
            product.setNoticeStartDate(cmd.getLongParam("startDate"));
            product.setNoticeEndDate(cmd.getLongParam("endDate"));
            product.setPublishUserId(loginId);
//            product.setType(PRODUCTTYPE.NOTICE.getValue());
            product.setCreateDate(new Date().getTime());
            product.setCreateUserID(loginId);
            product.setLatestDate(new Date().getTime());
            boolean temp = productService.addProduct(product);
            Response response = new Response();
            if (temp == true) {
                response.addValue("code", "0");
                response.addValue("info", "SUCCESS");
                cmd.setResponse(response);
            } else {
                response.addValue("code", "1");
                response.addValue("info", "发布公告失败");
                cmd.setResponse(response);
            }

        } catch (Exception ex) {
            Response response = new Response();
            response.addValue("code", "1");
            response.addValue("info", "发布公告失败");
            cmd.setResponse(response);
            logger.error("addProduct----cmd:" + cmd + ",exception:", ex);
        }

    }
    /**
     * 发布文章
     */
    public void publishArticle (Command cmd) {
        try {
            logger.info("-------publishNotice-------cmd:" + cmd);
            //公共参数 start
            String loginId=cmd.getStringParam("loginId");
            String appId=cmd.getStringParam("appId");
            String authorId=cmd.getStringParam("authorId");
            String version=cmd.getStringParam("version");
            String IMEI=cmd.getStringParam("IMEI");
            String model=cmd.getStringParam("model");
            String platform=cmd.getStringParam("platform");
            //公共参数 end
            int hasCover=cmd.getIntParam("hasCover");// 是否有封面，如果=1则图片第一章为封面
            String imgUrls="";//上传图片逻辑

            Product product = new Product();
            product.setAppid(appId);
            product.setImgUrls(imgUrls);
            product.setContent(cmd.getStringParam("content"));
            product.setTilte(cmd.getStringParam("title"));
            if(hasCover==1){
                product.setCover("第一张图片");
            }
            product.setPublishUserId(loginId);
//            product.setType(PRODUCTTYPE.ARTICLE.getValue());
            product.setCreateDate(new Date().getTime());
            product.setCreateUserID(loginId);
            product.setLatestDate(new Date().getTime());
            boolean temp = productService.addProduct(product);
            Response response = new Response();
            if (temp == true) {
                response.addValue("code", "0");
                response.addValue("info", "SUCCESS");
                cmd.setResponse(response);
            } else {
                response.addValue("code", "1");
                response.addValue("info", "发布文章失败");
                cmd.setResponse(response);
            }

        } catch (Exception ex) {
            Response response = new Response();
            response.addValue("code", "1");
            response.addValue("info", "发布文章失败");
            cmd.setResponse(response);
            logger.error("addProduct----cmd:" + cmd + ",exception:", ex);
        }

    }
}
