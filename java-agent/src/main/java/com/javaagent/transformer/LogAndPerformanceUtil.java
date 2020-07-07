package com.javaagent.transformer;

import java.util.Arrays;

/**
 * @Author: codefans
 * @Date: 2020-07-06 23:46
 */

public class LogAndPerformanceUtil {

    // 实现静态方法
    public static void showMethod(long startTime, Object[] Args) {
        System.out.println("方法耗时:" + (System.nanoTime() - startTime) / 1000000 + "ms, 方法入参：" + Arrays.toString(Args));
    }


}


//ASM操作字节码需要一定的学习才能理解，如果把上述字节码增强前后用Java代码表示大体入下：
//// ASM代码增强前
//public void test(int x) throws InterruptedException {
//        Thread.sleep(2000L);
//        System.out.println("i'm working " + x);
//        }
//
//
//// ASM代码增强后
//public void test(int x) throws InterruptedException {
//        long var2 = System.nanoTime();
//        Object[] var4 = new Object[]{new Integer(x)};
//        Thread.sleep(2000L);
//        System.out.println("i'm working " + x);
//        MethodContainer.showMethod(var2, var4);
//        }
//
//}
