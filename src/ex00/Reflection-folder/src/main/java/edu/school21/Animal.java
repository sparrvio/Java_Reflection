package edu.school21;

public interface Animal {
    String name = null;
    Integer age = 0;
    Double height = 0.0;

    void changeName(String newName);

    Integer growAge(Integer age);

    Double changeHeight(Double gram);

}


