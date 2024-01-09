package edu.school21;

public class Flower {
    private String color = null;
    private Double price = 0.0;
    private Integer amount = 0;


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

    int changeAmount(int quantity) {
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


