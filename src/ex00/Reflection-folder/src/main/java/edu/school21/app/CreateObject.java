package edu.school21.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static edu.school21.app.Program.printLine;
import static edu.school21.app.Program.scanner;

public class CreateObject {
    Class<?> classObj;

    public CreateObject(Class<?> classObj) {
        this.classObj = classObj;
    }

    public Object createObj() {
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
}
