package edu.school21.app;

import java.lang.reflect.Method;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        InfoClassInPackage infoClassInPackage = new InfoClassInPackage();
        Class<?> classObj = null;
        Object obj;
        try {
            classObj = Class.forName("edu.school21.models." + scanner.next());
        } catch (ClassNotFoundException e) {
            invalidInput();
            System.exit(1);
        }
        InfoFields infoFields = new InfoFields(classObj);
        InfoMethods infoMethods = new InfoMethods(classObj);
        CreateObject createObject = new CreateObject(classObj);
        obj = createObject.createObj();
        ChangeField changeField = new ChangeField(obj);
        obj = changeField.changeField();
        CallMethod callMethod = new CallMethod(obj);
        scanner.close();
    }

    static Scanner scanner = new Scanner(System.in);

    static void printLine() {
        for (int i = 0; i < 30; ++i) {
            System.out.print("-");
        }
        System.out.println();
    }

    static StringBuilder methodReturnType(Method mt) {
        Class<?>[] parameterTypes = mt.getParameterTypes();
        StringBuilder parameters = new StringBuilder();
        for (Class<?> pr : parameterTypes) {
            parameters.append(pr.getSimpleName());
        }
        return parameters;
    }

    static void invalidInput() {
        System.err.println("Invalid input");
        System.exit(1);
    }
}
