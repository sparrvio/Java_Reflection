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
}
