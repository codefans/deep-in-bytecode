package com.codefans.bytecode.javassist;

import javassist.*;

import java.lang.reflect.Field;

/**
 * @author: codefans
 * @date: 2019-06-25 15:00
 */
public class JavassistBytecodeProxy {

    public static Object newProxyInstance(Object delegate, Class cls, String newMethodStr) throws Exception {
        ClassPool mPool = new ClassPool(true);
        String className = cls.getName();
        System.out.println("className:" + className);

        CtClass mCtc = mPool.makeClass(className + "JavaassistProxy");
        mCtc.addInterface(mPool.get(className));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addField(CtField.make("public " + className + " delegate;", mCtc));
//        mCtc.addMethod(CtNewMethod.make("public int count() { return delegate.count(); }", mCtc));
        mCtc.addMethod(CtNewMethod.make(newMethodStr, mCtc));
        Class<?> pc = mCtc.toClass();
//        CountService bytecodeProxy = (CountService) pc.newInstance();
        Object bytecodeProxy = pc.newInstance();
        Field filed = bytecodeProxy.getClass().getField("delegate");
        filed.set(bytecodeProxy, delegate);
        return bytecodeProxy;
    }

}
