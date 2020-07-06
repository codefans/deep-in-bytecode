package com.codefans.bytecode.javassist;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * @author: codefans
 * @date: 2019-06-25 15:00
 */
public class JavassistProxy {

    public static Object newProxyInstance(final Class[] classes, MethodHandler methodHandler) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(classes);
        Class<?> proxyClass = proxyFactory.createClass();
        Object javassistProxy = (Object) proxyClass.newInstance();
        ((ProxyObject) javassistProxy).setHandler(methodHandler);
        return javassistProxy;
    }

}
