package com.ema.ema.models.car;

import com.ema.ema.models.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Table(name = "car")
@Entity
public class Car {
  @Id
  @GeneratedValue(generator = "uuid")
  private UUID uuid;

  @Column(name = "numberPlate", nullable = false)
  @NotNull(message = "numberPalate column cannot be null. Is mandatory")
  private String numberPlate;

  @Column(name = "vin")
  @NotNull(message = "vin column cannot be null. Is mandatory")
  private String vin;

  @Column(name = "manufacturer")
  @NotNull(message = "manufacturer column cannot be null. Is mandatory")
  private String manufacturer;

  @Column(name = "carState")
  @NotNull(message = "carState column cannot be null. Is mandatory")
  private CarState carState;

  @OneToOne
  @JoinColumn(name = "employee_id")
  @JsonIgnore
  private Employee employee;

  public Car(UUID uuid, String numberPlate, String vin, String manufacturer, CarState carState) {
    this.uuid = uuid;
    this.numberPlate = numberPlate;
    this.vin = vin;
    this.manufacturer = manufacturer;
    this.carState = carState;
  }

  public Car() {}

  public UUID getUuid() {
    return uuid;
  }

  public Car setUuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public String getNumberPlate() {
    return numberPlate;
  }

  public Car setNumberPlate(String numberPlate) {
    this.numberPlate = numberPlate;
    return this;
  }

  public String getVin() {
    return vin;
  }

  public Car setVin(String vin) {
    this.vin = vin;
    return this;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public Car setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

  public CarState getCarState() {
    return carState;
  }

  public Car setCarState(CarState carState) {
    this.carState = carState;
    return this;
  }

  public Employee getEmployee() {
    return employee;
  }

  public Car setEmployee(Employee person) {
    this.employee = person;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return Objects.equals(getUuid(), car.getUuid())
        && Objects.equals(getNumberPlate(), car.getNumberPlate())
        && Objects.equals(getVin(), car.getVin())
        && Objects.equals(getManufacturer(), car.getManufacturer())
        && getCarState() == car.getCarState()
        && Objects.equals(getEmployee(), car.getEmployee());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getUuid(), getNumberPlate(), getVin(), getManufacturer(), getCarState(), getEmployee());
  }

  @Override
  public String toString() {
    return "Car{"
        + "uuid="
        + uuid
        + ", numberPlate='"
        + numberPlate
        + '\''
        + ", vin='"
        + vin
        + '\''
        + ", manufacturer='"
        + manufacturer
        + '\''
        + ", carState="
        + carState
        + ", employee="
        + employee
        + '}';
  }
}
