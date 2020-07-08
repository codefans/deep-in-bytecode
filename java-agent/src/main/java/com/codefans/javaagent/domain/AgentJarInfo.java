/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AgentJarDomain
 * Author:   caishengzhi
 * Date:     2020/7/8 19:59
 * Description: agent这个jar包的信息
 */
package com.codefans.javaagent.domain;


import java.util.List;

/**
 *
 * agent这个jar包的信息
 *
 * @author caishengzhi
 * @date 2020/07/08 19:59
 * @since 1.0.0
 */
public class AgentJarInfo {

    /**
     * agent包的路径
     */
    private String jarPath;

    /**
     * 日志配置文件
     */
    private String logConfigPath;

    /**
     * agent包里的class列表
     */
    private List<String> classList;

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getLogConfigPath() {
        return logConfigPath;
    }

    public void setLogConfigPath(String logConfigPath) {
        this.logConfigPath = logConfigPath;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }
}