package edu.school21;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        printClassName();
        Scanner scanner = new Scanner(System.in);
        Class classObj = null;
        try {
            classObj = Class.forName("edu.school21." + "Cat");
//            classObj = Class.forName("edu.school21." + scanner.next());
        } catch (ClassNotFoundException e) {
            System.err.println("Invalid argument");
            System.exit(1);
        }
        printClassFields(classObj);
        printClassMethods(classObj);
        createObject(classObj);
    }

    private static void createObject(Class<?> classObj) {
        System.out.println("Let’s create an object.");
        Scanner scanner = new Scanner(System.in);
        Constructor<?>[] constructor = classObj.getConstructors();
        for (Constructor<?> cs : constructor) {
            Class<?>[] parameterTypes = cs.getParameterTypes();
            Object[] arguments = new Object[parameterTypes.length];
            Field[] fields = classObj.getDeclaredFields();
            if (parameterTypes.length > 0) {
                for (int i = 0; i < parameterTypes.length; ++i) {
                    if (parameterTypes[i] == String.class) {
                        System.out.println(fields[i].getName());
                        arguments[i] = scanner.nextLine();
                    } else if (parameterTypes[i].equals(Integer.class)) {
                        System.out.println(fields[i].getName());
                        arguments[i] = scanner.nextInt();
                        scanner.nextLine();
                    } else if (parameterTypes[i].equals(Double.class)) {
                        System.out.println(fields[i].getName());
                        arguments[i] = scanner.nextDouble();
                        scanner.nextLine();
                    } else if (parameterTypes[i].equals(Long.class)) {
                        System.out.println(fields[i].getName());
                        arguments[i] = scanner.nextLong();
                        scanner.nextLine();
                    } else if (parameterTypes[i].equals(Boolean.class)) {
                        System.out.println(fields[i].getName());
                        arguments[i] = scanner.nextBoolean();
                        scanner.nextLine();
                    }
                }
            }
            try {
                if (parameterTypes.length > 0) {
                    Object obj = cs.newInstance(arguments);
                    System.out.println("Created object: " + obj);
                }
            } catch (Exception e) {
                System.err.println("Input incorrect");
                System.exit(1);
            }
        }
    }

    private static void printClassFields(Class<?> classObj) {
        Field[] fields = classObj.getDeclaredFields();
        System.out.println("fileds:");
        for (Field fl : fields) {
            System.out.println("     " + fl.getType().getSimpleName() + " " + fl.getName());
        }
    }

    private static void printLine() {
        for (int i = 0; i < 20; ++i) {
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
        System.out.println(" - " + flowerClass.getSimpleName());
        printLine();
    }


    private static void printClassMethods(Class<?> classObj) {
        System.out.println("methods");
        Method[] method = classObj.getDeclaredMethods();
        for (Method mt : method) {
            Class<?>[] parameterTypes = mt.getParameterTypes();
            StringBuilder parameters = new StringBuilder();
            for (Class<?> pr : parameterTypes) {
                parameters.append(pr.getSimpleName());
            }
            System.out.println("     " + mt.getReturnType().getSimpleName() + " " +
                    mt.getName() + " " + "(" + parameters.toString() + ")");
        }
        printLine();
    }
}
