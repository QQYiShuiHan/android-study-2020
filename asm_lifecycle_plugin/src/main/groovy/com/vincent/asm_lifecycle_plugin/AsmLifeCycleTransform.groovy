package com.vincent.asm_lifecycle_plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import groovy.io.FileType
import org.apache.commons.io.FileUtils
import org.gradle.internal.impldep.org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter


public class AsmLifeCycleTransform extends Transform {

    @Override
    public String getName() {
        return "AsmLifeCycleTransform"
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    public boolean isIncremental() {
        return false
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        transformInputs.each { TransformInput transformInput ->
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File inputDir = directoryInput.file
                if (dir) {
                    dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        System.out.println(" find class : " + file.name)
                        ClassReader classReader = new ClassReader(file.bytes)
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor classVisitor = new LifeCycleClassVisitor(classWriter)
                        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                        byte[] bytes = classWriter.toByteArray();
                        FileOutputStream outputStream = new FileOutputStream(file.path)
                        outputStream.write(bytes)
                        outputStream.close()
                    }
                }
                File outputDir = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                        directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(inputDir, outputDir)
            }

            transformInput.jarInputs.each { JarInput jarInput ->
                File inputJar = jarInput.file
                if (inputFile) {

                }
                File outputJar = outputProvider.getContentLocation(jarInput.name, jarInput.contentTypes,
                        jarInput.scopes, Format.JAR)
                FileUtils.copyFile(inputJar, outputJar)
            }
        }
    }
}
