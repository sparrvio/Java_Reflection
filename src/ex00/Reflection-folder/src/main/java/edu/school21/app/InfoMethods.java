package edu.school21.app;

import java.lang.reflect.Method;

import static edu.school21.app.Program.methodReturnType;
import static edu.school21.app.Program.printLine;

public class InfoMethods {
    Class<?> classObj;
    Method[] methods;

    public InfoMethods(Class<?> classObj) {
        this.classObj = classObj;
        methods = classObj.getDeclaredMethods();
        printInfoMethods();
    }

    private void printInfoMethods() {
        System.out.println("methods:");
        for (Method mt : methods) {
            StringBuilder parameters = methodReturnType(mt);
            Class<?> returnType = mt.getReturnType();
            if (!returnType.toString().equals("void")) {
                System.out.println("     " + mt.getReturnType().getSimpleName() + " " +
                        mt.getName() + " " + "(" + parameters + ")");
            }
        }
        printLine();
    }
}
