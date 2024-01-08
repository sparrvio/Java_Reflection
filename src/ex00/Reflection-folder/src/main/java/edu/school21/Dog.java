package edu.school21;

public class Dog implements Animal {
    private String nameDog = null;
    private Integer age = 0;
    private Double height = 0.0;

    public Dog(String nameDog, Integer age, Double height) {
        this.nameDog = nameDog;
        this.age = age;
        this.height = height;
    }

    public Dog() {
        nameDog = "No name";
        age = 0;
        height = 0.0;
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
                "nameDog='" + nameDog + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
