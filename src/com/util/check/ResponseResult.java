package com.util.check;

/**
 * Created by lvliang on 2017/8/7.
 */
public class ResponseResult {
    public static String CODE = "code";
    public static String INFO = "info";
    public static String DATA = "data";

    public static enum ResultMsg{
        SUCCESS(0,"SUCCESS"),
        FAIL(1,"FAIL"),
        LOST_PARAM(2,"参数不完整");//参数缺失

        private int code;
        private String info;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        private ResultMsg(int code,String info) {
            this.code = code;
            this.info = info;
        }
    }

}
