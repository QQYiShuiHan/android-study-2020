package com.vincent.javassist_lifecycle_plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager


import org.apache.commons.io.FileUtils

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod

class JavassistLifeCycleTransform extends Transform {

    @Override
    public String getName() {
        return "JavassitLifeCycleTransform";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        System.out.println(" ***************** javassit transform is start *****************");
        transformInvocation.inputs.each { TransformInput transformInput ->
            transformInput.jarInputs.each { JarInput jarInput ->
                File inputFile = jarInput.file
                System.out.println("input getJarInput file name : " + inputFile.name);
                File outputFile = transformInvocation.outputProvider.getContentLocation(
                        jarInput.name,
                        jarInput.contentTypes,
                        jarInput.scopes,
                        Format.JAR)
                FileUtils.copyFile(inputFile, outputFile)
            }

            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File inputDir = directoryInput.file
                System.out.println(" input directory file name : " + directoryInput.name);
                File outputDir = transformInvocation.outputProvider.getContentLocation(
                        directoryInput.name,
                        directoryInput.contentTypes,
                        directoryInput.scopes,
                        Format.DIRECTORY
                )
                scanFilesAndInsertCode(inputDir.path)
                FileUtils.copyDirectory(inputDir, outputDir)
            }

        }
        System.out.println(" ***************** javassist transform is end *****************")
    }

    private void scanFilesAndInsertCode(String absolutePath) throws Exception {
        ClassPool classPool = ClassPool.getDefault()
        classPool.appendClassPath(absolutePath)
        CtClass ctClass = classPool.get("com.vincent.android_study_2020.TransformTest")
        if (ctClass == null) {
            return;
        }
        if (ctClass.isFrozen()) {
            ctClass.defrost()
        }
        CtMethod ctMethod = ctClass.getDeclaredMethod("init");
        ctMethod.insertAfter("System.out.println(\"this is insert code ... ^_^ .... \");")
        ctClass.writeFile(absolutePath)
        ctClass.detach()

    }
}
