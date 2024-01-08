package edu.school21;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;


public class Program {
    public static void main(String[] args)  {
        printClassName();
        Scanner scanner = new Scanner(System.in);
        Class classObj = null;
        try {
            classObj = Class.forName("edu.school21." + "Flower");
//            classObj = Class.forName("edu.school21." + scanner.next());
        } catch (ClassNotFoundException e) {
            System.err.println("Invalid argument");
            System.exit(1);
        }
        printClassFields(classObj);
        printClassMethods(classObj);
        createObject(classObj);
    }

    private static void createObject(Class<?> classObj)  {
        System.out.println("Let’s create an object.");
        Constructor<?>[] constructor = classObj.getConstructors();
        for (Constructor<?> cs : constructor){
            Class<?>[] parameterTypes = cs.getParameterTypes();
            if(parameterTypes.length > 0){

            }
        }
        Object obj = null;
        try {
            obj = classObj.getConstructor(String.class, Integer.class, Double.class).newInstance("newName", 10, 20.0);
        } catch (Exception e) {
            System.err.println("Invalid argument");
            System.exit(1);
        }
        System.out.println(obj.toString());

    }
    private static void printClassFields(Class<?> classObj) {
        Field[] fields = classObj.getFields();
        System.out.println("fileds:");
        for (Field fl : fields) {
            System.out.println("     " + fl.getType().getSimpleName() + " " + fl.getName());
        }
    }

    private static void printLine() {
        for (int i = 0; i < 20; ++i){
            System.out.print("-");
    }
        System.out.println();
    }
    private static void printClassName() {
        Class<Dog> dogClass = Dog.class;
        Class<Cat> catClass = Cat.class;
        Class<Flower> flowerClass = Flower.class;
        System.out.println("Classes:");
        System.out.println(" - " + catClass.getSimpleName());
        System.out.println(" - " + dogClass.getSimpleName());
        printLine();
    }


    private static void printClassMethods(Class<?> classObj) {
        System.out.println("methods");
        Method[] method = classObj.getDeclaredMethods();
        for (Method mt : method){
            Class<?>[] parameterTypes = mt.getParameterTypes();
            StringBuilder parameters = new StringBuilder();
            for (Class<?> pr : parameterTypes){
                parameters.append(pr.getSimpleName());
            }
            System.out.println("     " + mt.getReturnType().getSimpleName() + " " +
                    mt.getName() + " " + "(" + parameters.toString() + ")");
        }
        printLine();
    }
}
