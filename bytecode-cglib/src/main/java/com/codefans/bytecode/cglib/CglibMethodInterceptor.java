package com.codefans.bytecode.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: codefans
 * @date: 2019-05-23 10:06:15
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    public void before() {
        System.out.println("cglib proxy before method...");
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object resultObj = null;
        before();
        resultObj = methodProxy.invokeSuper(obj, objects);
        after();
        return resultObj;

    }

    public void after() {
        System.out.println("cglib proxy after method...");
    }


}
