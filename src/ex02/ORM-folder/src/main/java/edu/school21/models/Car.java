package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.persistence.GenerationType;

@OrmEntity(table = "simple_car")
public class Car {
    @OrmColumnId(id = GenerationType.IDENTITY)
    private Long id;

    @OrmColumn(name = "model", length = 10)
    private String model;

    @OrmColumn(name = "color", length = 10)
    private String color;

    @OrmColumn(name = "price")
    private Double price;

    public Car(String model, String color, Double price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
