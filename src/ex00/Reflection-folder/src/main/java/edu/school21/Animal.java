package edu.school21;

public interface Animal {
    String name = null;
    Integer age = 0;
    Double height = 0.0;
//    boolean gender = false;
//    Long price = null;

    void changeName(String newName);
    void growAge(int age);
    void changeHeight(Double gram);
//    void changePrice(Long rub);
}


