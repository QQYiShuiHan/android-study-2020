package com.vincent.android_study_2020.reflect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {


    private void myTestReflectApi() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Class clazz = Class.forName("com.vincent.android_study_2020.reflect.Student");

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            System.out.println(constructor);
        }

        Constructor constructor = clazz.getConstructor(String.class);
        Object string_constructor = constructor.newInstance("string constructor");

        Constructor charConstructor = clazz.getDeclaredConstructor(char.class);
        Object char_constructor = charConstructor.newInstance('男');

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field[] declaredField = clazz.getDeclaredFields();
        for (Field field : declaredField) {
            System.out.println(field);
        }

        Field name = clazz.getField("name");
        Object obj = clazz.getConstructor().newInstance();
        name.set(clazz, "刘德华");
        Student student = (Student) obj;
        System.out.println("验证姓名 ： " + student.name);

        Field phoneNum = clazz.getDeclaredField("phoneNum");
        phoneNum.setAccessible(true);
        phoneNum.set(clazz, "10086");
        System.out.println("验证电话号码 ： " + student);

        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }

        Method show1 = clazz.getMethod("show1", String.class);
        show1.invoke(clazz, "验证方法");
        Method show4 = clazz.getDeclaredMethod("show4", int.class);
        show4.setAccessible(true);
        show1.invoke(28);


        Class<?> mainClazz = Class.forName("com.vincent.android_study_2020.reflect.Student");

        Method main = mainClazz.getMethod("main", String[].class);
        main.invoke(mainClazz, new String[]{"a", "b", "c"});

        Class<?> aClass = Class.forName("java.lang.String");
        Object arr = Array.newInstance(aClass, 25);
        Array.set(arr, 0, "a");
        Array.set(arr, 1, "b");

        Class<?> className = null;
        try {
            className = Class.forName(getValue("className"));
            Method methodName = className.getDeclaredMethod(getValue("methodName"));
            methodName.setAccessible(true);
            methodName.invoke(className);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getValue(String naem) throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader("pro.txt");
        properties.load(fileReader);
        fileReader.close();
        String value = properties.getProperty(naem);
        return value;
    }


}
