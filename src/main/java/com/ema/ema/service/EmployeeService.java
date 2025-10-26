package com.ema.ema.service;

import com.ema.ema.exceptions.CannotBeCreated;
import com.ema.ema.exceptions.CannotBeModified;
import com.ema.ema.exceptions.NotFoundException;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.employee.Employee;
import com.ema.ema.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository er;
    private final CarService cs;

    public EmployeeService(EmployeeRepository er, CarService cs) {
        this.er = er;
        this.cs = cs;
    }

    public Employee create(Employee employee) {
        if (employee.getBadge() != null && this.exists(employee)) {
            throw new CannotBeCreated("Employee with existing badge");
        }
        try {
            return this.er.save(employee);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new CannotBeCreated("Entity cannot be created!");
        }
    }

    public Employee[] getAll() {
        return this.er.findAll()
                .toArray(Employee[]::new);
    }

    public Employee getById(UUID id) {
        return this.er.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @Transactional
    public boolean delete(UUID uuid) {
        Employee employee = this.getById(uuid);

        try {
            this.unassignCar(employee);
            this.er.deleteById(uuid);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Cannot be deleted");
        }
    }

    public boolean exists(Employee employee) {
        return this.er.existsByBadge(employee.getBadge());
    }

//    public Employee assignCar(UUID employeeID, UUID carID) {
//        Employee employee = this.getById(employeeID);
//        Car car = this.cs.getById(carID);
//        car.setEmployee(employee);
//
//        try {
//            return this.er.saveAndFlush(employee);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            throw new CannotBeModified("Entity cannot be modified!");
//        }
//    }

    public Employee unassignCar(Employee employee){
        Car car = employee.getCar();
        if(car != null){
            car.setEmployee(null);
            employee.setCar(null);
            this.cs.update(car);
            this.er.save(employee);
            return employee;
        }
        return employee;
    }

    public Employee assignCar(UUID employeeId, UUID carId){
        Employee employee = this.getById(employeeId);
        Car car = this.cs.getById(carId);
        car.setEmployee(employee);
        employee.setCar(car);

        this.cs.update(car);
        return this.er.save(employee);
    }
}
