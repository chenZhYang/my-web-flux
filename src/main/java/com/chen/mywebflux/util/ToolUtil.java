package com.chen.mywebflux.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: Aaron chen
 * @Date: 2020/5/19 0:16
 */
@Slf4j
public class ToolUtil {

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e){
            log.error(e.getMessage(),e);
        }
        return day;
    }

    public static SimpleDateFormat dataFormat(String format){
        return new SimpleDateFormat(format);
    }

    public static void main(String[] args) {
        long data = getDaySub("2020-01-01","2020-09-09");
        System.out.println(data);
    }
}
