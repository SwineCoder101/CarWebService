package com.test.car.CarWebService;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarCacheSingleton {
    private static Map<Integer,Car> carCache;

    private static CarCacheSingleton INSTANCE;

    private CarCacheSingleton(){
        carCache = new HashMap<>();
    }

    public static CarCacheSingleton getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new CarCacheSingleton();
        }
        return INSTANCE;
    }

    public Car getCar(int id){
        return carCache.get(id);
    }

    public void putCar(int id,Car car){
        carCache.put(id,car);
    }

    public boolean removeCar(int id){
        if (carCache.containsKey(id)){
            carCache.remove(id);
            return true;
        }
        return false;
    }

    public void clear(){
        carCache.clear();
    }

}
