package com.common.enums;

/**
 *产品类型枚举类
 * Created by luozi on 2017/7/20.
 */
public enum PRODUCTTYPE {
    NOTICE("公告", 1), ARTICLE("文章", 2), TOPIC("帖子", 3), DYNAMIC("动态", 4),LIVE("直播",5);
    private String name;
    private int value;

    PRODUCTTYPE(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
