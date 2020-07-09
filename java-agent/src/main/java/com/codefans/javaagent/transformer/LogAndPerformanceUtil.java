package com.codefans.javaagent.transformer;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Author: codefans
 * @Date: 2020-07-06 23:46
 */

public class LogAndPerformanceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LogAndPerformanceUtil.class);
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogAndPerformanceUtil.class);

    // 实现静态方法
    public static void showMethod(long startTime, Object[] Args) {
        try {
            String logInfo = "方法耗时:" + (System.nanoTime() - startTime) / 1000000 + "ms, 方法入参：" + Arrays.toString(Args);
            System.out.println(logInfo);
            LOG.info("showMethod, logInfo={}", logInfo);
            logger.info("showMethod, logInfo={}", logInfo);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("showMethod方法异常:", e);
            logger.error("showMethod方法异常:", e);
        } finally {
        }
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
