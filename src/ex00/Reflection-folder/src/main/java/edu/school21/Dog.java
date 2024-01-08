package edu.school21;

public class Dog implements Animal {
    String nameDog = null;
    Integer age = 0;
    Double height = 0.0;

    public Dog() {
        nameDog = "Dog";
        age = 5;
        height = 7.0;
    }
    public Dog(String name, Integer age, Double height) {
        this.nameDog = name;
        this.age = age;
        this.height = height;
    }


    @Override
    public void changeName(String newName) {
        this.nameDog = newName;
    }

    @Override
    public void growAge(int age) {
        this.age += age;
    }

    @Override
    public void changeHeight(Double gram) {
        this.height += gram;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "nameDog='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
