package com.vincent.asm_lifecycle_plugin;

import org.objectweb.asm.MethodVisitor;

import shadow.bundletool.com.android.tools.r8.org.objectweb.asm.Opcodes;

public class LifeCycleMethodVisitor extends MethodVisitor {

    private String className;
    private String methodName;


    public LifeCycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    // 方法执行前插入
    @Override
    public void visitCode() {
        super.visitCode();

        System.out.println("MethodVisitor visitCode -----------");

        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(className + " ------> " + methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }
}
