/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ParamParser
 * Author:   caishengzhi
 * Date:     2020/7/8 20:27
 * Description: 参数解析器
 */
package com.codefans.javaagent.parser;


import com.codefans.javaagent.domain.AgentParamInfo;

/**
 *
 * 参数解析器
 *
 * @author codefans
 * @date 2020/07/08 20:27
 * @since 1.0.0
 */
public class ParamParser {

    /**
     * 解析参数
     * @param paramStr
     * @return
     */
    public static AgentParamInfo parse(String paramStr) {
        AgentParamInfo agentParamInfo = new AgentParamInfo();
        if(paramStr != null && paramStr.trim().length() > 0) {
            if(paramStr.indexOf("&") >= 0) {
                String[] paramArr = paramStr.split("&");
                for(String param : paramArr) {
                    if(param.contains("agentJarPath")) {
                        agentParamInfo.setAgentJarPath(param.split("=")[1]);
                    } else if (param.contains("homePath")) {
                        agentParamInfo.setHomePath(param.split("=")[1]);
                    }
                }
            }
        }
        return agentParamInfo;
    }
}