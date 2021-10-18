package com.test.car.CarWebService;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class CarController {

    @PostMapping(path = "/add")
    public void add(@RequestBody Car car) {
    }

    @GetMapping(path = "/retrieve/{id}", produces = "application/json")
    public @ResponseBody Car retrieve(@PathVariable int id) {
        return new Car();
    }

    @PostMapping(path = "/remove/{id}")
    public void remove(@PathVariable int id){
    }


}
