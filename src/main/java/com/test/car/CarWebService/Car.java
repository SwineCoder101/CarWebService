package com.test.car.CarWebService;

import lombok.Data;
import java.time.Year;

@Data
public class Car {
    private int id;
    private String make;
    private String model;
    private String colour;
    private Year year;
}
