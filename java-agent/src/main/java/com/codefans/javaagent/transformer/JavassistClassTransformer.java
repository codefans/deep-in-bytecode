package com.codefans.javaagent.transformer;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Author: codefans
 * @Date: 2020-07-08 0:14
 */

public class JavassistClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 这里我们限制下，只针对目标包下进行耗时统计
        if (!className.startsWith("com/codefans/")) {
            return classfileBuffer;
        }

        try {

            if(className.endsWith("AsmBean")) {
                byte[] transformed = this.enhanceByBytecode(classfileBuffer);
                return transformed;
            } else {
                System.out.println("not transform [" + className + "]");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;

    }

    private byte[] enhanceByBytecode(byte[] classfileBuffer) throws IOException, CannotCompileException {
        byte[] content = null;
        ClassPool classPool = ClassPool.getDefault();
        CtClass cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

        for (CtMethod method : cl.getDeclaredMethods()) {
            // 所有方法，统计耗时；请注意，需要通过`addLocalVariable`来声明局部变量
            method.addLocalVariable("start", CtClass.longType);
            method.insertBefore("start = System.currentTimeMillis();");
            String methodName = method.getLongName();
            method.insertAfter("System.out.println(\"" + methodName + " cost: \" + (System.currentTimeMillis() - start));");
        }

        content = cl.toBytecode();
        return content;
    }



}
