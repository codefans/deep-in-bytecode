/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AgentStarter
 * Author:   codefans
 * Date:     2020/4/1 22:49
 * Description: 代理
 */
package com.codefans.javaagent;


import com.codefans.bytecode.common.util.ClassUtil;
import lombok.Data;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 *
 * 代理
 *
 * @author: codefans
 * @Date: 2020/04/01 22:49
 * @since: 1.0.0
 */
@Data
//@Slf4j
public class AgentStarter {

    /**
     * agent参数
     */
    String agentArgs;

    /**
     *
     */
    Instrumentation inst;

    /**
     * 构造方法
     * @param agentArgs
     * @param inst
     */
    public AgentStarter(String agentArgs, Instrumentation inst) {
        this.agentArgs = agentArgs;
        this.inst = inst;
    }

    public void start() {

        try {


            boolean isRedefineClassesSupported = inst.isRedefineClassesSupported();
            boolean isRetransformClassesSupported = inst.isRetransformClassesSupported();
            System.out.println("isRedefineClassesSupported:" + isRedefineClassesSupported);
            System.out.println("isRetransformClassesSupported:" + isRetransformClassesSupported);

//            String webPath = "D:\\github\\springmvc-jsf\\springmvc-jsf-consumer\\target\\springmvc-jsf-consumer\\";
            String webPath = agentArgs;
            MyWebAppClassLoader myClassLoader = new MyWebAppClassLoader(webPath);

            List<String> allClassList = ClassUtil.scanAllClass(webPath);
            for(int i = 0; i < allClassList.size(); i ++) {
                String clsName = allClassList.get(i);
                Class cls = myClassLoader.findClass(clsName);
                System.out.println("loaded Class=" + cls.getName());
            }

            Class[] allClassArr = inst.getAllLoadedClasses();
            System.out.println("class nums=" + allClassArr.length);

            String typeName = "";
            for(Class cls : allClassArr) {
    //            System.out.println(cls.getPackage() + "-" + cls.getName() + "-" + cls.getTypeName());
    //            System.out.println(cls.getName());
    //            System.out.println(cls.getTypeName());

                typeName = cls.getTypeName();
                if(typeName != null && typeName.startsWith("com.jd")) {
    //                log.info(cls.getTypeName());
                    System.out.println(cls.getTypeName());
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}