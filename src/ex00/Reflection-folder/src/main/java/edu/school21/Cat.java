package edu.school21;

public class Cat implements Animal {
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
    public Integer growAge(Integer age) {
        this.age += age;
        return this.age;
    }

    @Override
    public Double changeHeight(Double gram) {
        this.height += gram;
        return this.height;
    }


    @Override
    public String toString() {
        return "Cat[" +
                "nameCat='" + nameCat + '\'' +
                ", age=" + age +
                ", height=" + height +
                ']';
    }
}
