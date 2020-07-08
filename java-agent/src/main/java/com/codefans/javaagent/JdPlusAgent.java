/**
 * Copyright (C), 2015-2020, 京东
 * FileName: JdPlusAgent
 * Author:   caishengzhi
 * Date:     2020/4/1 11:39
 * Description: jd plus代理
 */
package com.codefans.javaagent;


import com.codefans.bytecode.common.util.DateUtil;

import java.lang.instrument.Instrumentation;
import java.util.Date;

/**
 *
 * jd plus代理
 *
 * @author: codefans
 * @Date: 2020/04/01 11:39
 * @since: 1.0.0
 */
public class JdPlusAgent {

    private static Instrumentation instrumentation;

    /**
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain two args, agentArgs=" + agentArgs + ", time=" + DateUtil.format(new Date()));

        new AgentStarter(agentArgs, inst).start();
        instrumentation = inst;

    }

    /**
     * 获取对象的大小
     * @param o
     * @return
     */
    public static long sizeOf(Object o) {
        return instrumentation.getObjectSize(o);
    }

    /**
     *
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
        System.out.println("premain one arg, agentArgs=" + agentArgs + ", time=" + DateUtil.format(new Date()));
    }


}