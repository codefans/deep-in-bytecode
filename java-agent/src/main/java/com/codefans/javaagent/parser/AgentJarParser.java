/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AgentJarParser
 * Author:   caishengzhi
 * Date:     2020/7/8 20:01
 * Description: agent这个jar包的解析器
 */
package com.codefans.javaagent.parser;


import com.codefans.javaagent.domain.AgentJarInfo;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * agent这个jar包的解析器
 *
 * @author caishengzhi
 * @date 2020/07/08 20:01
 * @since 1.0.0
 */
public class AgentJarParser {

    /**
     * 解析agent包
     * @param agentJarPath
     * @return
     */
    public static AgentJarInfo parseAgent(String agentJarPath) {
        AgentJarInfo agentJarInfo = null;
        try {

            agentJarInfo = new AgentJarInfo();
            agentJarInfo.setJarPath(agentJarPath);

            System.out.println("jarPath=" + agentJarPath);
            JarFile jarFile = new JarFile(new File(agentJarPath), false);
            Enumeration<JarEntry> enumeration = jarFile.entries();
            JarEntry jarEntry = null;
            String entryName = "";
            Class cls = null;
            List<String> classList = new ArrayList<>();

            while (enumeration.hasMoreElements()) {
                jarEntry = enumeration.nextElement();
                if (jarEntry.isDirectory()) {
                    continue;
                }
                entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    entryName = formatClassName(entryName);
                    if (entryName.endsWith("LogAndPerformanceUtil")) {
                        System.out.println("加载[LogAndPerformanceUtil]这个类会抛异常");
                    }
//                    cls = classLoader.loadClass(entryName);
                    System.out.println("clsName=" + entryName + ", className=" + cls.getName());
                    classList.add(entryName);

                } else if (entryName.equals("log4j2_async_logger.xml")) {
                    agentJarInfo.setLogConfigPath(entryName);
                } else {

                }
            } //end while

            agentJarInfo.setClassList(classList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentJarInfo;
    }

    /**
     * 解析jar包
     * @param jarPath
     * @return
     */
    public static List<String> parseJar(String jarPath) {

        List<String> classList = new ArrayList<>();
        try {

            JarFile jarFile = new JarFile(new File(jarPath));
            Enumeration<JarEntry> enumeration = jarFile.entries();
            JarEntry jarEntry = null;
            String entryName = "";
            Class cls = null;


            while (enumeration.hasMoreElements()) {
                jarEntry = enumeration.nextElement();
                if (jarEntry.isDirectory()) {
                    continue;
                }
                entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    entryName = formatClassName(entryName);
                    if (entryName.endsWith("LogAndPerformanceUtil")) {
                        System.out.println("加载[LogAndPerformanceUtil]这个类会抛异常");
                    }
//                    cls = classLoader.loadClass(entryName);
                    System.out.println("clsName=" + entryName + ", className=" + cls.getName());
                    classList.add(entryName);

                }
            } //end while

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    /**
     * 格式化类名
     * @param className
     * @return
     */
    public static String formatClassName(String className) {
        String formatClassName = className.replace("/", ".").substring(0, className.length() - 6);
        return formatClassName;
    }

}