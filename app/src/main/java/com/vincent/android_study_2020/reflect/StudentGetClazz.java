package com.vincent.android_study_2020.reflect;

public class StudentGetClazz {


    /**
     * 一般java中获取class的方法有一下三种
     *
     * @return
     */
    public Class getStudentClass() {
        Student student = new Student();
        //第一种方式获取Class对象
        Class getClazz = student.getClass();
        //第二种方式获取Class对象
        Class staticClazz = Student.class;
        //第三种方式获取Class对象
        Class packAndClassName = null;
        try {
            packAndClassName = Class.forName("com.vincent.android_study_2020.reflect.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return packAndClassName;
    }


    public void crateClassInstance(Class clazz) {
        Student student = null;
        try {
            student = (Student) clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


}
