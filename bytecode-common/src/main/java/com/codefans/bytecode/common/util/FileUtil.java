/**
 * Copyright (C), 2015-2020, 京东
 * FileName: FileUtil
 * Author:   caishengzhi
 * Date:     2020/7/8 18:09
 * Description: 文件操作
 */
package com.codefans.bytecode.common.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * 文件操作
 *
 * @author codefans
 * @date 2020/07/08 18:09
 * @since 1.0.0
 */
public class FileUtil {

    private String filePath;
    private BufferedWriter bw;

    private static class Instance {
        private static FileUtil instance = new FileUtil();
    }

    public static FileUtil getInstance() {
        return Instance.instance;
    }


    /**
     * 私有化构造方法，使外部无法通过构造方法构造除instance外的类实例 从而达到单例模式控制类实例数目的目的
     */
    private FileUtil() {
    }

    public FileUtil getLogger(String _filePath) throws IOException {
        if(filePath == null) {
            filePath = _filePath;
            File file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
            this.bw = new BufferedWriter(new FileWriter(file));
        }
        return getInstance();
    }

    public void append(String line) throws IOException {
        this.bw.append(line);
    }

    public void close() throws IOException {
        this.bw.flush();
        this.bw.close();
        this.bw = null;
    }

}