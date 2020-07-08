/**
 * Copyright (C), 2015-2020, 京东
 * FileName: LoggerAdapter
 * Author:   caishengzhi
 * Date:     2020/7/8 20:09
 * Description: 日志适配
 */
package com.codefans.javaagent.log;


/**
 *
 * 日志适配
 *
 * @author codefans
 * @date 2020/07/08 20:09
 * @since 1.0.0
 */
public class LoggerAdapter {

    public static void initLogger(String logConfigPath) {
        initLog4j(logConfigPath);
    }

    public static void initLog4j(String logConfigPath) {
        System.setProperty("log4j.configurationFile", logConfigPath);
    }

}