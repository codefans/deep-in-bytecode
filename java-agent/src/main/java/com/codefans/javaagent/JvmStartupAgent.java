package com.codefans.javaagent;

import com.codefans.javaagent.transformer.AsmClassTransformer;
import com.codefans.javaagent.transformer.JavassistClassTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @Author: codefans
 * @Date: 2020-07-08 0:13
 */

public class JvmStartupAgent {

    /**
     * JVM启动时agent
     */
    public static void premain(String args, Instrumentation inst) {
        agent0(args, inst);
    }

    public static void agent0(String args, Instrumentation inst) {
        System.out.println("agent is running!");

        try {

//            inst.addTransformer(new ClassFileTransformer() {
//                @Override
//                public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
//                    // JVM加载的所有类会流经这个类转换器
//                    // 这里找到自定义的测试类
//                    if (className.endsWith("WorkerMain")) {
//                        System.out.println("transform class WorkerMain");
//                    }
//                    else if (className.endsWith("AsmBean")) {
//                        System.out.println("transform class AsmBean");
//                    }
//                    else if (className.endsWith("OriginStarter")) {
//                        System.out.println("transform class OriginStarter");
//                    } else {
//                        System.out.println("transform class: " + className);
//                    }
//                    // 直接返回原本的字节码
//                    return classfileBuffer;
//                }
//            }, true);

            inst.addTransformer(new JavassistClassTransformer(), true);
//
//            String className = "";
//            Class<?> c = null;
//
//            Class[] classes = inst.getAllLoadedClasses();
//            for(Class cls :classes){
//                System.out.println("getAllLoadedClasses-->" + cls.getName());
//            }
//
////            找到OriginStarter类，对其进行重定义
//            className = "com.codefans.javaagent.OriginStarter";
//            c = Class.forName(className);
//            inst.retransformClasses(c);

        } catch (Exception e) {
            System.out.println("exception:" + e);
        } catch (Error e) {
            System.out.println("error:" + e);
            e.printStackTrace();
        }
    }

}
