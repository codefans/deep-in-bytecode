/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ClassUtil
 * Author:   caishengzhi
 * Date:     2020/4/17 9:21
 * Description: 类工具
 */
package com.javaagent.util;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * 类工具
 *
 * @author: caishengzhi
 * @Date: 2020/04/17 09:21
 * @since: 1.0.0
 */
public class ClassUtil {

    /**
     * 扫描所有类名
     * @param webRoot
     * @return
     */
    public static List<String> scanAllClass(String webRoot) {
        String libPath = webRoot + File.separator + "WEB-INF" + File.separator + "lib";
        String classesPath = webRoot + File.separator + "WEB-INF" + File.separator + "classes";
        List<String> allList = new ArrayList<>();
        List<String> jarClassList = scanJarClass(libPath);
        List<String> classList = new ArrayList<>();
        scanClass(classesPath, classesPath, classList);
//        allList.addAll(jarClassList);
        allList.addAll(classList);
        return allList;
    }

    /**
     * 扫描libPath目录下所有jar包中的类名
     * @param libPath
     * @return
     */
    public static List<String> scanJarClass(String libPath) {
        List<String> jarClassList = new ArrayList<>();

        List<String> jarList = new ArrayList<>();
        gatherAllJar(libPath, jarList);

        if(jarList != null && jarList.size() > 0) {
            for(int i = 0; i < jarList.size(); i ++) {
                try {
                    JarFile jarFile = new JarFile(jarList.get(i));
                    Enumeration<JarEntry> enumeration = jarFile.entries();
                    JarEntry jarEntry = null;
                    while(enumeration.hasMoreElements()) {
                        jarEntry = enumeration.nextElement();
                        jarClassList.add(jarEntry.getName());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return jarClassList;
    }

    public static void gatherAllJar(String libPath, List<String> jarList) {
        File libDir = new File(libPath);
        if(libDir.isDirectory()) {
            File[] fileList = libDir.listFiles();
            for(int i = 0; i < fileList.length; i ++) {
                File file = fileList[i];
                if(file.isDirectory()) {
                    gatherAllJar(libPath + File.separator + file.getName(), jarList);
                } else {
                    jarList.add(file.getAbsolutePath());
                }
            }
        } else {
            jarList.add(libDir.getAbsolutePath());
        }
    }

    /**
     * 扫描classesPath目录下的所有类
     * @param root
     * @param currentPath
     * @param classList
     * @return
     */
    public static void scanClass(String root, String currentPath, List<String> classList) {
        File dir = new File(currentPath);
        if(dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            for(int i = 0; i < fileList.length; i ++) {
                File file = fileList[i];
                if(file.isDirectory()) {
                    scanClass(root, currentPath + File.separator + file.getName(), classList);
                } else {
                    if(file.getName().endsWith(".class")) {
                        String absolutePath = file.getAbsolutePath();
                        String className = absolutePathToName(root, absolutePath);
                        classList.add(className);
                    }
                }
            }
        } else {
            String absolutePath = dir.getAbsolutePath();
            String className = absolutePathToName(root, absolutePath);
            classList.add(className);
        }
    }

    public static String absolutePathToName(String root, String absolutePath) {
        absolutePath = absolutePath.substring(absolutePath.indexOf(root) + root.length() + 1);
//        System.out.println(absolutePath);
        absolutePath = absolutePath.replaceAll(File.separator + File.separator, ".");
//        System.out.println(absolutePath);
        absolutePath = absolutePath.substring(0, absolutePath.indexOf(".class"));
        return absolutePath;
    }

}