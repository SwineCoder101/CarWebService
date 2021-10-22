package com.test.car.CarWebService;

import lombok.Data;

@Data
public class Car {

    public Car(){
    }

    public Car(int id, String make, String model, String colour, int year){
        this.id=id;
        this.make=make;
        this.model=model;
        this.colour=colour;
        this.year=year;
    }

    private int id;
    private String make;
    private String model;
    private String colour;
    private int year;
}
