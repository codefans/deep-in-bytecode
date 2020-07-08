package com.codefans.javaagent.transformer;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Author: codefans
 * @Date: 2020-07-06 23:24
 */

public class AsmClassTransformer implements ClassFileTransformer {

    /** The internal form class name of the class to transform */
    private String targetClassName;
    /** The class loader of the class we want to transform */
    private ClassLoader targetClassLoader;

    public AsmClassTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    public AsmClassTransformer() {
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // 对类字节码进行操作
        // 这里需要注意，不能对classfileBuffer这个数组进行修改操作
        try {

            if(className.startsWith("com/codefans")) {
                System.out.println("AsmClassTransformer.transform(), className=" + className);
            }

//            if(targetClassName.equals(className) && targetClassLoader.equals(loader)) {

//                System.out.println("start enhance class:[" + className + "]");
                // 创建ASM ClassReader对象，导入需要增强的对象字节码
                ClassReader reader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

                // 自己实现的代码增强器
                LogAndPerformanceEnhancer myEnhancer = new LogAndPerformanceEnhancer(classWriter);

                // 增强字节码
                reader.accept(myEnhancer, ClassReader.SKIP_FRAMES);

                // 返回MyEnhancer增强后的字节码
                return classWriter.toByteArray();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }

        // return null 则不会对类进行转换
        return null;
    }


}
