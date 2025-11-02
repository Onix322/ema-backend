package com.ema.ema.controller;

import com.ema.ema.handler.responseHandler.ResponseHandler;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.response.Response;

import com.ema.ema.service.CarEmployeeManager;
import com.ema.ema.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final CarEmployeeManager carEmployeeManager;

    public CarController(CarService carService, CarEmployeeManager carEmployeeManager) {
        this.carService = carService;
        this.carEmployeeManager = carEmployeeManager;
    }

    @PostMapping("/create")
    public ResponseEntity<Response<Car>> create(@Valid @RequestBody Car car) {
        return ResponseHandler.created(this.carService.create(car));
    }

    @GetMapping("/get")
    public ResponseEntity<Response<Car[]>> getAll(){
        return ResponseHandler.ok(this.carService.getAll());
    }

    @GetMapping("/get/available")
    public ResponseEntity<Response<Car[]>> getAllAvailable(){
        return ResponseHandler.ok(this.carService.getAllAvailable());
    }

    @GetMapping("/get/{carId}")
    public ResponseEntity<Response<Car>> getById(@PathVariable UUID carId){
        return ResponseHandler.ok(this.carService.getById(carId));
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Car>> update(@RequestBody Car car){
        return ResponseHandler.ok(this.carService.update(car));
    }

    @PutMapping("/state")
    public ResponseEntity<Response<Car>> changeState(@RequestBody Car car){
        return ResponseHandler.ok(this.carService.changeState(car));
    }

    @DeleteMapping("/delete/{carID}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable(value = "carID") UUID carId) {
        return ResponseHandler.ok(this.carEmployeeManager.delete(carId));
    }
}
