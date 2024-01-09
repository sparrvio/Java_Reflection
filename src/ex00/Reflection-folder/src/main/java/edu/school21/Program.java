package edu.school21;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        printClassName();
        Scanner scanner = new Scanner(System.in);
        Class classObj = null;
        try {
            classObj = Class.forName("edu.school21." + scanner.next());
        } catch (ClassNotFoundException e) {
            invalidInput();
            System.exit(1);
        }
        printClassFields(classObj);
        printClassMethods(classObj);
        Object obj = createObject(classObj, scanner);
        changeField(obj, scanner);
        try {
            callMethod(obj, scanner);
        } catch (InvocationTargetException | IllegalAccessException e) {
            invalidInput();
        }
        scanner.close();
    }

    private static void callMethod(Object obj, Scanner scanner) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of the method for call:");
        Class<?> classObj = obj.getClass();
        Method[] methods = classObj.getDeclaredMethods();
        boolean containsName = false;
        Method methodForCall = null;
        if (scanner.hasNextLine()) {
            String nameMethod = scanner.nextLine();
            for (Method mt : methods) {
                if (nameMethod.equals(mt.getName() + " (" + methodReturnType(mt) + ")")) {
                    methodForCall = mt;
                    containsName = true;
                    break;
                }
            }
        } else {
            invalidInput();
        }

        if (!containsName) {
            invalidInput();
        } else {
            System.out.println("Enter int value:");
            methodForCall.setAccessible(true);
            for (int i = 0; i < methodForCall.getParameterCount(); ++i) {
                String type = String.valueOf(methodReturnType(methodForCall));
                if (type.equals("String")) {
                    if (scanner.hasNextLine()) {
                        System.out.println("Method returned:");
                        System.out.println(methodForCall.invoke(obj, scanner.nextLine()));
                    } else {
                        invalidInput();
                    }
                } else if (type.equals("Integer")) {
                    if (scanner.hasNextInt()) {
                        System.out.println("Method returned:");
                        System.out.println(methodForCall.invoke(obj, scanner.nextInt()));
                    } else {
                        invalidInput();
                    }
                } else if (type.equals("Double")) {
                    if (scanner.hasNextDouble()) {
                        System.out.println("Method returned:");
                        System.out.println(methodForCall.invoke(obj, scanner.nextDouble()));
                    } else {
                        invalidInput();
                    }
                } else if (type.equals("Long")) {
                    if (scanner.hasNextLong()) {
                        System.out.println("Method returned:");
                        System.out.println(methodForCall.invoke(obj, scanner.nextLong()));
                    } else {
                        invalidInput();
                    }
                } else if (type.equals("boolean")) {
                    if (scanner.hasNextBoolean()) {
                        System.out.println("Method returned:");
                        System.out.println(methodForCall.invoke(obj, scanner.nextBoolean()));
                    } else {
                        invalidInput();
                    }
                }
            }
        }
    }

    private static void changeField(Object obj, Scanner scanner) {
        System.out.println("Enter name of the field for changing:");
        Class<?> classObj = obj.getClass();
        String fieldName = null;
        String newFieldName;
        Field[] fields = classObj.getDeclaredFields();
        if (!scanner.hasNextLine()) {
            System.err.println("No input provided");
            System.exit(1);
        } else {
            fieldName = scanner.nextLine();
        }

        String finalFieldName = fieldName;
        if (Arrays.stream(fields).anyMatch(field -> finalFieldName.toString().equals(field.getName()))) {

            System.out.println("Enter String value:");
            if (!scanner.hasNextLine()) {
                invalidInput();
            } else {
                newFieldName = scanner.nextLine();
                try {
                    Field field = classObj.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(obj, newFieldName);
                    System.out.println("Object updated: " + obj);
                    printLine();

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            System.err.println("Incorrect Input");
            System.exit(1);
        }
    }

    private static Object createObject(Class<?> classObj, Scanner scanner) {
        scanner.reset();
        System.out.println("Let’s create an object.");
        Object obj = null;
        Constructor<?>[] constructor = classObj.getConstructors();
        for (Constructor<?> cs : constructor) {
            Class<?>[] parameterTypes = cs.getParameterTypes();
            Object[] arguments = new Object[parameterTypes.length];
            Field[] fields = classObj.getDeclaredFields();
            if (parameterTypes.length > 0) {
                for (int i = 0; i < parameterTypes.length; ++i) {
                    if (parameterTypes[i] == String.class) {
                        scanner.nextLine();
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
                    obj = cs.newInstance(arguments);
                    System.out.println("Created object: " + obj);
                    printLine();
                }
            } catch (Exception e) {
                System.err.println("Input incorrect");
                System.exit(1);
            }
        }
        return obj;
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
        Method[] methods = classObj.getDeclaredMethods();
        for (Method mt : methods) {
            StringBuilder parameters = methodReturnType(mt);
            Class<?> returnType = mt.getReturnType();
            if (!returnType.toString().equals("void")) {
                System.out.println("     " + mt.getReturnType().getSimpleName() + " " +
                        mt.getName() + " " + "(" + parameters.toString() + ")");
            }
        }
        printLine();
    }

    private static StringBuilder methodReturnType(Method mt) {
        Class<?>[] parameterTypes = mt.getParameterTypes();
        StringBuilder parameters = new StringBuilder();
        for (Class<?> pr : parameterTypes) {
            parameters.append(pr.getSimpleName());
        }
        return parameters;
    }

    private static void invalidInput() {
        System.err.println("Invalid input");
        System.exit(1);
    }
}
