package com.javaagent.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.objectweb.asm.commons.Method;

/**
 * @Author: codefans
 * @Date: 2020-07-06 23:27
 */

public class LogAndPerformanceEnhancer extends ClassVisitor implements Opcodes {
    public LogAndPerformanceEnhancer(int api) {
        super(api);
    }

    public LogAndPerformanceEnhancer(ClassVisitor classVisitor) {
        super(ASM8, classVisitor);
    }

    public LogAndPerformanceEnhancer(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


    /**
     * 对字节码中的方法定义进行修改
     */
    @Override
    public MethodVisitor visitMethod(int access, final String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (isIgnore(mv, access, name)) {
            return mv;
        }
        return new AdviceAdapter(Opcodes.ASM8, new JSRInlinerAdapter(mv, access, name, descriptor, signature, exceptions), access, name, descriptor) {


            private final Type LOG_PERFORMANCE_UTIL = Type.getType(LogAndPerformanceUtil.class);
            private int timeIdentifier;
            private int argsIdentifier;


            /**
             * 进入方法前
             */
            @Override
            protected void onMethodEnter() {
                // 调用System.nanoTime()方法，将方法出参推入栈顶
                invokeStatic(Type.getType(System.class), Method.getMethod("long nanoTime()"));
                // 构造一个Long类型的局部变量，然后返回这个变量的标识符
                timeIdentifier = newLocal(Type.LONG_TYPE);


                // 存储栈顶元素也就是System.nanoTime()返回值，到指定位置本地变量区
                storeLocal(timeIdentifier);


                // 加载入参数组，将入参数组ref推入栈顶
                loadArgArray();
                // 构造一个Object[]类型的局部变量，返回这个变量的标识符
                argsIdentifier = newLocal(Type.getType(Object[].class));
                // 存储入参到指定位置本地变量区
                storeLocal(argsIdentifier);
            }


            @Override
            protected void onMethodExit(int opcode) {
                // 加载指定位置的本地变量到栈顶
                loadLocal(timeIdentifier);
                loadLocal(argsIdentifier);
                // 相当于调用LogAndPerformanceUtil.showMethod(long, Object[])方法
                invokeStatic(LOG_PERFORMANCE_UTIL, Method.getMethod("void showMethod(long,Object[])"));
            }


        };
    }


    /**
     * 方法是否需要被忽略（静态构造函数和构造函数）
     */
    private boolean isIgnore(MethodVisitor mv, int access, String methodName) {
        return null == mv
                || isAbstract(access)
                || isFinalMethod(access)
                || "<clinit>".equals(methodName)
                || "<init>".equals(methodName);
    }


    private boolean isAbstract(int access) {
        return (ACC_ABSTRACT & access) == ACC_ABSTRACT;
    }


    private boolean isFinalMethod(int methodAccess) {
        return (ACC_FINAL & methodAccess) == ACC_FINAL;
    }

}
