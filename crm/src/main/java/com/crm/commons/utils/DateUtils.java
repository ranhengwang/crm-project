package com.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DateUtils
 * Package:
 * Project:crm-project
 * Description:对date类型数据进行处理的工具类
 *
 * @Date:2022/9/13 18:14
 * @Author:wang ranheng
 */
public class DateUtils {
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(date);
        return nowStr;
    }
}
