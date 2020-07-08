/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AgentParamInfo
 * Author:   caishengzhi
 * Date:     2020/7/8 20:28
 * Description: agent参数
 */
package com.codefans.javaagent.domain;


/**
 *
 * agent参数
 *
 * @author codefans
 * @date 2020/07/08 20:28
 * @since 1.0.0
 */
public class AgentParamInfo {

    /**
     * agent包的路径
     */
    private String agentJarPath;

    /**
     * agent目录
     */
    private String homePath;

    public String getAgentJarPath() {
        return agentJarPath;
    }

    public void setAgentJarPath(String agentJarPath) {
        this.agentJarPath = agentJarPath;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }
}