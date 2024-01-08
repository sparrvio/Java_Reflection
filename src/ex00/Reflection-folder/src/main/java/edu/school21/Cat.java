package edu.school21;

public class Cat implements Animal{
    private String nameCat = null;
    private Integer age = 0;
    private Double height = 0.0;


    public Cat(String nameCat, Integer age, Double height) {
        this.nameCat = nameCat;
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
                "nameCat='" + nameCat + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
