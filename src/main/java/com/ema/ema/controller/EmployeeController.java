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
    private ResponseEntity<Response<Object>> create(@RequestBody Employee employee){
        return ResponseHandler.created(this.es.create(employee));
    }

    @GetMapping("/get")
    private ResponseEntity<Response<Object>> getAll(){
        return ResponseHandler.ok(this.es.getAll());
    }

    @GetMapping("/get/{employeeID}")
    private ResponseEntity<Response<Object>> get(@PathVariable UUID employeeID){
        return ResponseHandler.ok(this.es.getById(employeeID));
    }

    @DeleteMapping("/delete/{employeeID}")
    private ResponseEntity<Response<Object>> delete(@PathVariable("employeeID") UUID employeeId){
        return ResponseHandler.ok(this.es.delete(employeeId));
    }

    @PatchMapping("/assign/{employeeID}/{carID}")
    public Employee assignCar(@PathVariable UUID employeeID, @PathVariable UUID carID){
        return this.es.assignCar(employeeID, carID);
    }
}
