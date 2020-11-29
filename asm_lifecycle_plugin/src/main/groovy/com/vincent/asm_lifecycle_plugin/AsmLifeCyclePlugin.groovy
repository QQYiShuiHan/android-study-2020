package com.vincent.asm_lifecycle_plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project


public class AsmLifeCyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        System.out.println("<========= asm life cycle plugin start ... ===========>")
        def android = target.extensions.getByType(AppExtension)
//        AsmLifeCycleTransform asmTransform = new AsmLifeCycleTransform()
//        android.registerTransform(asmTransform)
    }
}

