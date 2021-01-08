package main;

public class Example extends ExampleParent implements Runnable{
    private int age = 3;
    private String color = "red";
    public String name = "Ann";
    public int number = 7;

    public Example() {
    }

    public Example(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void run() {

    }
}
