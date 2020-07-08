package com.codefans.javaagent;

import com.codefans.javaagent.context.StartupAgentContext;
import com.codefans.javaagent.transformer.AsmClassTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * @Author: codefans
 * @Date: 2020-07-08 0:13
 */
public class JvmStartupAgent {

    private static Logger log;

    /**
     * JVM启动时agent
     */
    public static void premain(String paramStr, Instrumentation inst) {
        agent0(paramStr, inst);
    }

    public static void agent0(String paramStr, Instrumentation inst) {
        System.out.println("agent is running!, args=" + paramStr);

        try {

//            inst.addTransformer(new JavassistClassTransformer());
//            inst.addTransformer(new JavassistClassTransformer(), true);
            inst.addTransformer(new AsmClassTransformer());

            StartupAgentContext.contextInit(paramStr);

            log = LoggerFactory.getLogger(JvmStartupAgent.class);

        } catch (Exception e) {
            System.out.println("exception:");
            System.out.println(e);
            log.error("agent启动异常:", e);
        } catch (Error e) {
            System.out.println("error:");
            System.out.println(e);
            e.printStackTrace();
            log.error("agent启动错误:", e);
        }
    }

}
