/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ClassLoaderHelp
 * Author:   caishengzhi
 * Date:     2020/7/8 20:15
 * Description: 类加载工具类
 */
package com.codefans.javaagent.loader;


import com.codefans.bytecode.common.util.CollectionUtil;
import com.codefans.javaagent.parser.AgentJarParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 类加载工具类
 *
 * @author codefans
 * @date 2020/07/08 20:15
 * @since 1.0.0
 */
public class ClassLoaderHelp {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(ClassLoaderHelp.class);

    /**
     * 加载指定类
     * @param classList
     */
    public static void loadClass(List<String> classList) {

        //ClassLoader classLoader = ClassLoader.getSystemClassLoader();

//        URL url = new URL("file:" + jarPath);
//        ClassLoader classLoader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String className = "";
            for(int i = 0; i < classList.size(); i ++) {
                className = classList.get(i);
                classLoader.loadClass(className);
            }
        } catch (Exception e) {
            LOG.error("类加载异常,", e);
            e.printStackTrace();
        } catch (Error e) {
            LOG.error("类加载错误,", e);
            e.printStackTrace();
        }
    }

    /**
     * 加载lib目录下的所有jar包
     * @param libPath
     */
    public static void loadLib(String libPath) {

        List<String> jarList = new ArrayList<>();
        gatherJarList(libPath, jarList);

        List<String> allClass = new ArrayList<>();
        List<String> tempList = null;
        String jarPath = "";
        for(int i = 0; i < jarList.size(); i ++) {
            jarPath = jarList.get(i);
            tempList = AgentJarParser.parseJar(jarPath);
            if(CollectionUtil.isNotEmpty(tempList)) {
                allClass.addAll(tempList);
            }
        }

        ClassLoaderHelp.loadClass(allClass);

    }

    public static void gatherJarList(String libPath, List<String> jarList) {
        File dir = new File(libPath);
        if(dir.isDirectory()) {
            File[] fileList = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });
            File file = null;
            for(int i = 0; i < fileList.length; i ++) {
                file = fileList[i];
                if(file.isDirectory()) {
                    gatherJarList(libPath + File.separator + file.getName(), jarList);
                } else {
                    jarList.add(file.getAbsolutePath());
                }
            }
        } else {
            if(dir.getName().endsWith(".jar")) {
                jarList.add(dir.getAbsolutePath());
            }
        }
    }

}