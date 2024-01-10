package edu.school21.app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

public class InfoClassInPackage {
    InfoClassInPackage() {
        printClassName();
    }

    public void printClassName() {
        Reflections reflections = new Reflections("edu.school21.models", new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        System.out.println("Classes:");
        for (Class<?> cl : classes) {
            if (!cl.isInterface()) {
                System.out.println("    - " + cl.getSimpleName());
            }
        }
    }
}