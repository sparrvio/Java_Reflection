package edu.school21.app;

import java.lang.reflect.Field;
import java.util.Arrays;

import static edu.school21.app.Program.*;

public class ChangeField {
    private final Object obj;

    public ChangeField(Object obj) {
        this.obj = obj;
    }

    public Object changeField() {
        System.out.println("Enter name of the field for changing:");
        Class<?> classObj = this.obj.getClass();
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
        if (Arrays.stream(fields).anyMatch(field -> finalFieldName.equals(field.getName()))) {

            System.out.println("Enter " + getTypeFieldInString(finalFieldName) + " value:");
            if (!scanner.hasNextLine()) {
                invalidInput();
            } else {
                newFieldName = scanner.nextLine();
                try {
                    Field field = classObj.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (field.getType() == Integer.class) {
                        field.set(this.obj, Integer.parseInt(newFieldName));
                    } else if (field.getType() == Double.class) {
                        field.set(this.obj, Double.parseDouble(newFieldName));
                    } else if (field.getType() == Long.class) {
                        field.set(this.obj, Long.parseLong(newFieldName));
                    } else if (field.getType() == Boolean.class) {
                        field.set(this.obj, Boolean.parseBoolean(newFieldName));
                    } else {
                        field.set(this.obj, newFieldName);
                    }
                    System.out.println("Object updated: " + this.obj);
                    printLine();
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.err.println("Incorrect Input");
            System.exit(1);
        }
        return this.obj;
    }

    private String getTypeFieldInString(String fieldName) {
        Class<?> classObj = this.obj.getClass();
        Field field1;
        try {
            field1 = classObj.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        Class<?> fieldType = field1.getType();
        return fieldType.getSimpleName();
    }
}
