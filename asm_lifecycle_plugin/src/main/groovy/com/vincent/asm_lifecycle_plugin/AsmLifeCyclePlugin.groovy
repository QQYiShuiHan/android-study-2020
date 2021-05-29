package com.vincent.asm_lifecycle_plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * ASM 动态编译
 * 好的博客 https://blog.csdn.net/a296777513/article/details/90665134
 */
class AsmLifeCyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        System.out.println("<========= asm life cycle plugin start ... ===========>")
        def android = target.extensions.getByType(AppExtension)
        AsmLifeCycleTransform asmTransform = new AsmLifeCycleTransform()
        android.registerTransform(asmTransform)
    }
}

