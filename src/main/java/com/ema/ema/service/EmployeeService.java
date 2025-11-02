package com.ema.ema.service;

import com.ema.ema.exceptions.CannotBeCreated;
import com.ema.ema.exceptions.NotFoundException;
import com.ema.ema.models.car.Car;
import com.ema.ema.models.car.CarState;
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

    // CREATE

    public Employee create(Employee initialEmployee) {
        if (initialEmployee.getBadge() != null && this.exists(initialEmployee)) {
            throw new CannotBeCreated("Employee with existing badge");
        }
        try {
            Employee savedEmployee = this.er.saveAndFlush(initialEmployee);
            Car employeeCar = initialEmployee.getCar();

            if (employeeCar != null) {
                employeeCar.setEmployee(savedEmployee);
                this.assignCar(savedEmployee.getUuid(), employeeCar.getUuid());
            }
            return savedEmployee;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new CannotBeCreated("Entity cannot be created!");
        }
    }

    // READ

    public Employee[] getAll() {
        return this.er.findAll()
                .toArray(Employee[]::new);
    }

    public Employee getById(UUID id) {
        return this.er.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    // UPDATE -- MODIFY

    public Employee update(Employee employee) {
        if (employee.getUuid() == null) {
            throw new RuntimeException("UUID must not be null");
        }
        System.out.println(employee);
        Car employeeCar = employee.getCar();
        if (employeeCar == null) {
            this.unassignCar(employee.getUuid());
        } else {
            employeeCar.setEmployee(employee);
            this.unassignCar(employee.getUuid());
            this.assignCar(employee.getUuid(), employeeCar.getUuid());
        }
        return this.er.saveAndFlush(employee);
    }

    public Employee unassignCar(UUID employeeID) {
        return this.unassignCar(this.getById(employeeID));
    }

    public Employee unassignCar(Employee employee) {
        Car car = employee.getCar();
        if (car != null) {
            try {
                car.setEmployee(null);
                car.setCarState(CarState.AVAILABLE);
                this.cs.update(car);
                employee.setCar(null);
                return employee;
            } catch (Exception e) {
                throw new RuntimeException("Car cannot be unassign");
            }
        }
        return employee;
    }

    public Employee assignCar(UUID employeeId, UUID carId) {
        try {
            Employee employee = this.getById(employeeId);
            Car car = this.cs.getById(carId);
            car.setEmployee(employee);
            car.setCarState(CarState.ASSIGNED);
            this.cs.update(car);
            employee.setCar(car);
            return employee;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Car cannot be assign");
        }
    }

    public Employee autoAssignCar(UUID employeeID) {
        Car[] cars = this.cs.getAllAvailable();
        Car car = cars[(int) (Math.random() * cars.length)];
        return this.assignCar(employeeID, car.getUuid());
    }

    // DELETE

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

}
