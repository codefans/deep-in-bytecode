/**
 * Copyright (C), 2015-2020, 京东
 * FileName: OriginStarter
 * Author:   caishengzhi
 * Date:     2020/7/7 11:24
 * Description: 启动类
 */
package com.codefans.javaagent;


import java.util.Random;

/**
 *
 * 启动类
 *
 * @author caishengzhi
 * @date 2020/07/07 11:24
 * @since 1.0.0
 */
public class OriginStarter {

//    public static void main(String[] args) {
//
//        AsmBean asmBean = null;
//        String param = "";
//        String result = "";
//
//        while(true) {
//            asmBean = new AsmBean();
//            param = "张三,zhangsan";
//            result = asmBean.doSomething(param);
//            System.out.printf("param=%s, result=%s, time=%s\n", param, result, DateUtil.format(new Date()));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                ClassLoader currentLoader = ClassLoader.getSystemClassLoader();
//                currentLoader.loadClass("com.codefans.javaagent.OriginStarter");
//                currentLoader.loadClass("com.codefans.bytecode.common.AsmBean");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            int x = new Random().nextInt();
            new OriginStarter().test(x);
        }
    }


    public void test(int x) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("i'm working " + x);
    }

}