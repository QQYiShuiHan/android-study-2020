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


/**
 * Javassit 动态编译的过程
 * 一个比较好的入门博客 https://blog.csdn.net/Double2hao/article/details/105173087
 */
class JavassistLifeCycleTransform extends Transform {

    //用于指明本Transform的名字，也是代表该Transform的task的名字
    @Override
    public String getName() {
        return "JavassistLifeCycleTransform";
    }

    //用于指明Transform的输入类型，可以作为输入过滤的手段。
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    //用于指明Transform的作用域
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    //是否增量编译
    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        System.out.println(" ***************** javassit transform is start *****************");
        //遍历jar文件 对jar不操作，但是要输出到out路径
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

            //遍历文件，在遍历过程中
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
        //将当前路径加入类池,不然找不到这个类
        classPool.appendClassPath(absolutePath)
        CtClass ctClass = classPool.get("com.vincent.android_study_2020.TransformTest")
        if (ctClass == null) {
            return;
        }
        if (ctClass.isFrozen()) {
            ctClass.defrost()
        }
        CtMethod ctMethod = ctClass.getDeclaredMethod("init");
        //在方法末尾插入代码
        ctMethod.insertAfter("System.out.println(\"---->this is insert code ... ^_^ .... \");")
        ctClass.writeFile(absolutePath)
        //释放
        ctClass.detach()

    }
}
