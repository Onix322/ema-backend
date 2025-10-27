package com.ema.ema.service;

import com.ema.ema.exceptions.NotFoundException;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.car.CarState;
import com.ema.ema.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car create(Car car) {
        return this.carRepository.save(car);
    }

    public Car update(Car car) {
        if (car.getUuid() == null) {
            throw new RuntimeException("UUID must not be null");
        }
        return this.carRepository.saveAndFlush(car);
    }

    public boolean delete(UUID carID) {
        try {
            Car car = this.getById(carID);
            car.setEmployee(null);
            this.update(car);
            this.carRepository.deleteById(carID);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Cannot be deleted");
        }
    }

    public Car getById(UUID carId) {
        return this.carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car entity not found!"));
    }

    public Car[] getAll() {
        return this.carRepository.findAll()
                .toArray(Car[]::new);
    }

    public Car[] getAllAvailable(){
        return this.carRepository.findAllByCarState(CarState.AVAILABLE);
    }

    public Car changeState(Car car) {
        Car carFound = this.getById(car.getUuid());
        carFound.setCarState(car.getCarState());

        return this.update(carFound);
    }
}
