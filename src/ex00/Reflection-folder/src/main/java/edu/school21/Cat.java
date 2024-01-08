package edu.school21;

public class Cat implements Animal{
    String nameCat = null;
    Integer age = 0;
    Double height = 0.0;


    public Cat(String name, Integer age, Double height) {
        this.nameCat = name;
        this.age = age;
        this.height = height;
    }
    public Cat() {
        nameCat = "No name";
        age = 0;
        height = 0.0;
    }

    @Override
    public void changeName(String newName) {
        this.nameCat = newName;
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
        return "Cat{" +
                "nameCat='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
