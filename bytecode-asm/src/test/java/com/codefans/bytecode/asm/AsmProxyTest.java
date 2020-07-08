/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AsmProxyTest
 * Author:   codefans
 * Date:     2020/7/6 17:26
 * Description: asm代理测试
 */
package com.codefans.bytecode.asm;


import com.codefans.bytecode.common.AsmBean;
import org.junit.Test;

/**
 *
 * asm代理测试
 *
 * @author codefans
 * @date 2020/07/06 17:26
 * @since 1.0.0
 */
public class AsmProxyTest {

    @Test
    public void asmMethodProxyTest() throws Exception {

        AsmBean asmBean = new AsmBean();
        AsmBean asmBeanProxy = (AsmBean) AsmProxy.newProxyInstance(asmBean, AsmBean.class, "doSomething");
        String param = "zhangsan";
        String res = asmBeanProxy.doSomething(param);
        System.out.println("param:" + param + ", res:" + res);



    }

}