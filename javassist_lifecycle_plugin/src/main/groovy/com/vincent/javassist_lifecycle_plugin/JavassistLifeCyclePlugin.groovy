package com.vincent.javassist_lifecycle_plugin;

import com.android.build.gradle.BaseExtension

import org.gradle.api.Plugin
import org.gradle.api.Project

class JavassistLifeCyclePlugin implements Plugin<Project> {
	@Override
	public void apply(Project project) {
		System.out.println("<========= javassit life cycle plugin start ... ===========>");
		BaseExtension android = project.getExtensions().getByType(BaseExtension.class)
		JavassistLifeCycleTransform transform = new JavassistLifeCycleTransform()
		android.registerTransform(transform)
	}
}
