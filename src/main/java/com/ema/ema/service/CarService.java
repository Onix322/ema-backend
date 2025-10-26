package com.ema.ema.service;

import com.ema.ema.exceptions.CannotBeModified;
import com.ema.ema.exceptions.NotFoundException;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.employee.Employee;
import com.ema.ema.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car create(Car car){
        return this.carRepository.save(car);
    }

    public Car update(Car car){
        return this.carRepository.save(car);
    }

    public Car getById(UUID carId){
        return this.carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Entity not found!"));
    }

    public Car[] getAll(){
        return this.carRepository.findAll()
                .toArray(Car[]::new);
    }
}
