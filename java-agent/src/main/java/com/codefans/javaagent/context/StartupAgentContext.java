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
         * 初始化日志
         */
        String logConfigPath = agentJarInfo.getLogConfigPath();
        LoggerAdapter.initLogger(logConfigPath);

        /**
         * 加载agent包的所有类
         */
        List<String> classList = agentJarInfo.getClassList();
        ClassLoaderHelp.loadClass(classList);

    }

}