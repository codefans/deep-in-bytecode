/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ControllerTransformer
 * Author:   codefans
 * Date:     2020/4/19 19:05
 * Description: controller transformer
 */
package com.codefans.javaagent.transformer;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 *
 * controller transformer
 *
 * @author: codefans
 * @Date: 2020/04/19 19:05
 * @since: 1.0.0
 */
public class ControllerTransformer implements ClassFileTransformer {

    private String targetClassName;//被增强类的类名
    private String targetMethodName;//被增强的方法名

    public ControllerTransformer(String targetClassName, String targetMethodName) {
        this.targetClassName = targetClassName;
        this.targetMethodName = targetMethodName;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        if (className.equals(targetClassName)) {
            CtClass ctclass = null;
            try {
                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
                CtMethod ctmethod = ctclass.getDeclaredMethod(targetMethodName);// 得到这方法实例
                ctmethod.insertBefore("System.out.println(1111111);");
                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}