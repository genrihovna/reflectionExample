package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        printClassName(new Example());

        printMethods(new Example());

        printConstructor(new Example());

        printGetttersSetters(new Example());

        printPublicFields(new Example());

        printPrivateField(new Example(), "color");
        printPrivateField(new Example(), "age");
    }

    static void printClassName(Object obj){
        System.out.println("The class of " + obj.toString() +
                " is " + obj.getClass().getSimpleName());
    }

    static void printMethods(Object obj){
        Method[] methods = obj.getClass().getMethods();
        for(Method method : methods)
            System.out.println("method = " + method.getName());
    }

    static void printConstructor(Object obj){
        Constructor[] constructors = obj.getClass().getConstructors();
        for(Constructor constructor : constructors){
            System.out.println("constructor = " + constructor.getName());
        }
    }

    public static void printGetttersSetters(Object obj){

        Method[] methods = obj.getClass().getMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println(
                    "getter: " + method.getName());
            if(isSetter(method)) System.out.println(
                    "setter: " + method.getName());
        }
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))
            return false;
        if(method.getParameterTypes().length != 0)
            return false;
        if(void.class.equals(method.getReturnType()))
            return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set"))
            return false;
        if(method.getParameterTypes().length != 1)
            return false;
        return true;
    }

    static void printPublicFields(Object obj){
        Class theClass = obj.getClass();
        Field[] fields = theClass.getFields();
        for(Field field : fields) {
            try{
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                System.out.println("public field " + fieldName + " = " + fieldValue);
            }
            catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    static void printPrivateField(Object obj, String fieldName){
       try {
           Field privateField = obj.getClass().getDeclaredField(fieldName);
           privateField.setAccessible(true);
           Object fieldValue = privateField.get(obj);
           System.out.println("private field " + fieldName + " value is " + fieldValue);
       }
       catch(NoSuchFieldException ex){
           System.out.println("check field name");
       }
       catch (IllegalAccessException ex){
           ex.printStackTrace();
       }
    }
}