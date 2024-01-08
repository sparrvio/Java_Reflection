package edu.school21;

public class Flower {
    String color = null;
    Double price = 0.0;
    Integer amount = 0;

    public Flower(){
        String color = "No color";
        Double price = -1.0;
        Integer amount = -1;
    }

    public Flower(String color, Double price, Integer amount) {
        this.color = color;
        this.price = price;
        this.amount = amount;
    }

    int changeAmount(int quantity){
        this.amount += quantity;
        return amount;
    }
    String changeColor(String color){
        this.color = color;
        return color;
    }

    Double changePrice(Double price){
        this.price += price;
        return price;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}


