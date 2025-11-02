package com.ema.ema.service;

import com.ema.ema.models.car.Car;
import com.ema.ema.models.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarEmployeeManager {

    private final CarService carService;
    private final EmployeeService employeeService;

    public CarEmployeeManager(CarService carService, EmployeeService employeeService) {
        this.carService = carService;
        this.employeeService = employeeService;
    }

    // DELETE
    public boolean delete(UUID carID) {
        try {
            Car car = this.carService.getById(carID);
            Employee employee = car.getEmployee();
            if (employee != null) {
                employee.setCar(null);
                employeeService.update(employee);
            }
            car.setEmployee(null);
            this.carService.update(car);
            this.carService.delete(carID);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Cannot be deleted");
        }
    }
}
