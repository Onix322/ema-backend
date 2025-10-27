package com.ema.ema.repository;

import com.ema.ema.models.car.Car;
import com.ema.ema.models.car.CarState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    Car[] findAllByCarState(CarState carState);
}
