package edu.school21.app;

import java.lang.reflect.Field;

public class InfoFields {
    Class<?> classObj;
    Field[] fields;

    public InfoFields(Class<?> classObj) {
        this.classObj = classObj;
        fields = this.classObj.getDeclaredFields();
        printInfoFields();
    }

    private void printInfoFields() {
        System.out.println("fields:");
        for (Field fl : this.fields) {
            System.out.println("     " + fl.getType().getSimpleName() + " " + fl.getName());
        }
    }
}
