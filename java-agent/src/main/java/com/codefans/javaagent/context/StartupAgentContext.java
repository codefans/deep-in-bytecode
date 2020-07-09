/**
 * Copyright (C), 2015-2020, 京东
 * FileName: StartupAgentContext
 * Author:   caishengzhi
 * Date:     2020/7/8 20:23
 * Description: 启动agent上下文
 */
package com.codefans.javaagent.context;


import com.codefans.javaagent.domain.AgentJarInfo;
import com.codefans.javaagent.domain.AgentParamInfo;
import com.codefans.javaagent.loader.ClassLoaderHelp;
import com.codefans.javaagent.log.LoggerAdapter;
import com.codefans.javaagent.parser.AgentJarParser;
import com.codefans.javaagent.parser.ParamParser;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * 启动agent上下文
 *
 * @author codefans
 * @date 2020/07/08 20:23
 * @since 1.0.0
 */
public class StartupAgentContext {

    /**
     * 初始化
     * @param paramStr
     */
    public static void contextInit(String paramStr) {

        /**
         * 解析参数
         */
        AgentParamInfo agentParamInfo = ParamParser.parse(paramStr);

        /**
         * 解析agent包
         */
        String agentJarPath = agentParamInfo.getAgentJarPath();
        AgentJarInfo agentJarInfo = AgentJarParser.parseAgent(agentJarPath);

        /**
         * 加载lib包
         */
        String homePath = agentParamInfo.getHomePath();
        ClassLoaderHelp.loadLib(homePath);

        /**
         * 加载agent包的所有类
         */
        List<String> classList = agentJarInfo.getClassList();
        ClassLoaderHelp.loadClass(classList);

        /**
         * 初始化日志
         */
        String logConfigPath = agentJarInfo.getLogConfigPath();
        String configFilePath = "classpath:" + logConfigPath;
        System.out.println("configFilePath=" + configFilePath);
        LoggerAdapter.initLogger(configFilePath);

        try {

//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//
//            File log4jFile = new File(configFilePath);
//            ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
//            Configurator.initialize(classLoader, source);

            Logger log = LoggerFactory.getLogger(StartupAgentContext.class);
            log.info("日志初始化完成!!!!!");

            org.apache.logging.log4j.Logger logger = LogManager.getLogger(StartupAgentContext.class);
            logger.info("log4j2日志初始化完成!!!!!");

            System.out.println("log4j2日志初始化完成!!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}