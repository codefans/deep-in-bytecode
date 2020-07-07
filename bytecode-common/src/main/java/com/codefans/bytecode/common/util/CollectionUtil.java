/**
 * Copyright (C), 2015-2020, 京东
 * FileName: CollectionUtil
 * Author:   caishengzhi
 * Date:     2020/4/17 9:25
 * Description: 集合工具类
 */
package com.codefans.bytecode.common.util;


import java.util.List;

/**
 *
 * 集合工具类
 *
 * @author: caishengzhi
 * @Date: 2020/04/17 09:25
 * @since: 1.0.0
 */
public class CollectionUtil {

    /**
     * 打印list
     * @param list
     */
    public static void print(List<String> list) {
        if(list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i));
            }
        }
    }



}