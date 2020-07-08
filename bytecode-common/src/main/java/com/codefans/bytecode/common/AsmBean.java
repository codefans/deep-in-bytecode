/**
 * Copyright (C), 2015-2020, 京东
 * FileName: AsmBean
 * Author:   caishengzhi
 * Date:     2020/7/6 17:30
 * Description: asm测试bean
 */
package com.codefans.bytecode.common;


/**
 *
 * asm测试bean
 *
 * @author caishengzhi
 * @date 2020/07/06 17:30
 * @since 1.0.0
 */
public class AsmBean {

    /**
     * doSomething方法
     * @param param
     * @return
     */
    public String doSomething(String param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + param;
    }

}