package com.ema.ema.models.employee;

import com.ema.ema.models.car.Car;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    @Column(name = "name", nullable = false)
    @NotNull(message = "name column cannot be null. Is mandatory")
    private String name;

    @Column(name = "workingHours", nullable = false)
    @NotNull(message = "workingHours column cannot be null. Is mandatory")
    private Integer workingHours;

    @Column(name = "badge", unique = true, nullable = true)
    private Long badge;

    @Column(name = "role", nullable = false)
    @NotNull(message = "role column cannot be null. Is mandatory")
    private UserRole role;

    @OneToOne(mappedBy = "employee")
    private Car car;

    public Employee(UUID uuid, String name, Integer workingHours, Long badge, Car car, UserRole role) {
        this.uuid = uuid;
        this.name = name;
        this.workingHours = workingHours;
        this.badge = badge;
        this.car = car;
        this.role = role;
    }

    public Employee() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public Employee setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public Employee setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
        return this;
    }

    public Long getBadge() {
        return badge;
    }

    public Employee setBadge(Long badge) {
        this.badge = badge;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Employee setCar(Car car) {
        this.car = car;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public Employee setRole(UserRole role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee person = (Employee) o;
        return Objects.equals(getUuid(), person.getUuid()) && Objects.equals(getName(), person.getName()) && Objects.equals(getWorkingHours(), person.getWorkingHours()) && Objects.equals(getBadge(), person.getBadge()) && Objects.equals(getCar(), person.getCar()) && getRole() == person.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getName(), getWorkingHours(), getBadge(), getCar(), getRole());
    }

    @Override
    public String toString() {
        return "Person{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", workingHours=" + workingHours +
                ", badge=" + badge +
                ", car=" + car +
                ", role=" + role +
                '}';
    }
}
