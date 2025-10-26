package com.ema.ema.controller;

import com.ema.ema.handler.responseHandler.ResponseHandler;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.response.Response;
import com.ema.ema.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Car>> create(@Valid @RequestBody Car car) {
        return ResponseHandler.created(this.carService.create(car));
    }

    @GetMapping("/get")
    public ResponseEntity<Response<Car[]>> getAll(){
        return ResponseHandler.ok(this.carService.getAll());
    }

    @GetMapping("/get/{carId}")
    public Car getById(@PathVariable UUID carId){
        return null;
    }

    @DeleteMapping("/delete/{carID}")
    public boolean delete(@PathVariable(value = "carID") UUID carId) {
        return false;
    }
}
