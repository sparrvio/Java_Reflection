package edu.school21;

public class Cat implements Animal{
    String name = null;
    Integer age = 0;
    Double height = 0.0;
    boolean gender = false;
    Long price = null;

    public Cat() {
        name = "Cat";
        age = 3;
        height = 3.0;
        gender = false;
        price = 1L;
    }

    public Cat(String name, Integer age, Double height, boolean gender, Long price) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.price = price;
    }

    @Override
    public void changeName(String newName) {
        this.name = newName;
    }

    @Override
    public void growAge() {
        this.age ++;
    }

    @Override
    public void changeHeight(Double gram) {
        this.height += gram;
    }

    @Override
    public void changePrice(Long rub) {
        this.price += rub;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", gender=" + gender +
                ", price=" + price +
                '}';
    }
}
