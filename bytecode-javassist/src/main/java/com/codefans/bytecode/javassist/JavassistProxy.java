package com.codefans.bytecode.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author: codefans
 * @date: 2019-06-25 15:00
 */
public class JavassistProxy {

    /**
     *
     * @param classes
     * @param methodHandler
     * @return
     * @throws Exception
     */
    public static Object newProxyInstance(final Class[] classes, MethodHandler methodHandler) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(classes);
        Class<?> proxyClass = proxyFactory.createClass();
        Object javassistProxy = (Object) proxyClass.newInstance();
        ((ProxyObject) javassistProxy).setHandler(methodHandler);
        return javassistProxy;
    }

    /**
     *
     * @param classes
     * @param filtedMethodName 过滤的方法名称
     * @param methodHandler
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object newProxyInstance(final Class[] classes, Set<String> filtedMethodName, MethodHandler methodHandler) throws IllegalAccessException, InstantiationException {
        byte[] content = null;
        // 实例化代理类工厂
        ProxyFactory factory = new ProxyFactory();

        //设置父类，ProxyFactory将会动态生成一个类，继承该父类
        factory.setInterfaces(classes);

        //设置过滤器，判断哪些方法调用需要被拦截
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                return filtedMethodName.contains(m.getName());
            }
        });

        Class<?> clazz = factory.createClass();
        Object javassistProxy = (Object) clazz.newInstance();
        ((ProxyObject)javassistProxy).setHandler(methodHandler);

        return javassistProxy;
    }


}
