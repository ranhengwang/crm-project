package com.crm.commons.utils;

import java.util.UUID;

/**
 * ClassName:UUIDUtils
 * Package:
 * Project:crm-project
 * Description:获取UUID的字符串
 *
 * @Date:2022/9/18 17:39
 * @Author:wang ranheng
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
