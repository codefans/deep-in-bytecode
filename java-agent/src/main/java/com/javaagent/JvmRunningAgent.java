package com.javaagent;

import com.codefans.bytecode.asm.AsmClassTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @Author: codefans
 * @Date: 2020-07-07 8:33
 */

public class JvmRunningAgent {

    /**
     * JVM启动时agent
     */
    public static void premain(String args, Instrumentation inst) {
        agent0(args, inst);
    }


    /**
     * JVM运行时agent
     */
    public static void agentmain(String args, Instrumentation inst) {
        agent0(args, inst);
    }


    public static void agent0(String args, Instrumentation inst) {
        System.out.println("agent is running!");
        inst.addTransformer(new AsmClassTransformer());

        try {
            String className = "com.codefans.bytecode.common.AsmBean";
            // 找到WorkerMain类，对其进行重定义
//            Class<?> c = Class.forName("test.WorkerMain");
            Class<?> c = Class.forName(className);
            inst.retransformClasses(c);
        } catch (Exception e) {
            System.out.println("error!");
        }
    }

}
