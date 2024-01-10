package edu.school21.models;

public class Flower {
    private String color;
    private Double price;
    private Integer amount;


    public Flower(String color, Double price, Integer amount) {
        this.color = color;
        this.price = price;
        this.amount = amount;
    }

    public Flower() {
        color = "No color";
        price = -1.0;
        amount = -1;
    }

    Integer changeAmount(Integer quantity) {
        this.amount += quantity;
        return amount;
    }

    String changeColor(String color) {
        this.color = color;
        return color;
    }

    Double changePrice(Double price) {
        this.price += price;
        return price;
    }

    @Override
    public String toString() {
        return "Flower[" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ']';
    }
}


