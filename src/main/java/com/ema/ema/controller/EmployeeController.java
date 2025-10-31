package com.ema.ema.controller;

import com.ema.ema.handler.responseHandler.ResponseHandler;
import com.ema.ema.models.employee.Employee;
import com.ema.ema.models.response.Response;
import com.ema.ema.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService es;

    public EmployeeController(EmployeeService es) {
        this.es = es;
    }

    @PostMapping("/create")
    private ResponseEntity<Response<Employee>> create(@RequestBody Employee employee) {
        return ResponseHandler.created(this.es.create(employee));
    }

    @GetMapping("/get")
    private ResponseEntity<Response<Employee[]>> getAll() {
        return ResponseHandler.ok(this.es.getAll());
    }

    @GetMapping("/get/{employeeID}")
    private ResponseEntity<Response<Employee>> get(@PathVariable UUID employeeID) {
        return ResponseHandler.ok(this.es.getById(employeeID));
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Employee>> update(@RequestBody Employee employee) {
        return ResponseHandler.ok(this.es.update(employee));
    }

    @DeleteMapping("/delete/{employeeID}")
    private ResponseEntity<Response<Boolean>> delete(@PathVariable UUID employeeID) {
        return ResponseHandler.ok(this.es.delete(employeeID));
    }

    @PatchMapping("/assign/{employeeID}/{carID}")
    public ResponseEntity<Response<Employee>> assignCar(@PathVariable UUID employeeID, @PathVariable UUID carID) {
        return ResponseHandler.ok(this.es.assignCar(employeeID, carID));
    }

    @PatchMapping("/unassign/{employeeID}")
    public ResponseEntity<Response<Employee>> unassignCurrentCar(@PathVariable UUID employeeID) {
        return ResponseHandler.ok(this.es.unassignCar(employeeID));
    }

    @PatchMapping("/assign/auto/{employeeID}")
    public ResponseEntity<Response<Employee>> autoAssignCar(@PathVariable UUID employeeID) {
        return ResponseHandler.ok(this.es.autoAssignCar(employeeID));
    }
}