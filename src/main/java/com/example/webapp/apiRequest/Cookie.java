package com.example.webapp.apiRequest;

import java.util.Date;

public class Cookie {
    private static Date nowDate;
    private static String  cookStr;

    public static Date getNowDate() {
        return nowDate;
    }

    public static void setNowDate(Date nowDate) {
        Cookie.nowDate = nowDate;
    }

    public static String getCookStr() {
        return cookStr;
    }

    public static void setCookStr(String cookStr) {
        Cookie.cookStr = cookStr;
    }
}
