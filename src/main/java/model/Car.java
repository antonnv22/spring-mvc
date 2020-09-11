package model;

public class Car {
    private Integer id;
    private String color;
    private String model;

    public Car() {
    }

    public Car(Integer id, String model, String color) {
        this.id = id;
        this.color = color;
        this.model = model;
    }

    public Integer getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}