package com.test.car.CarWebService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class CarController {

    @PostMapping(path = "/add")
    public ResponseEntity<Car> add(@RequestBody Car car) {
        CarCacheSingleton.getInstance().putCar(car.getId(),car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping(path = "/retrieve/{id}", produces = "application/json")
    public ResponseEntity<Car> retrieve(@PathVariable int id) {
        if (CarCacheSingleton.getInstance().getCar(id) !=null){
            return new ResponseEntity<>(CarCacheSingleton.getInstance().getCar(id),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new Car(),HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable int id){
        Boolean hasRemoved=CarCacheSingleton.getInstance().removeCar(id);
        if (hasRemoved){
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }


}
