package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.Car;
import com.didier.carmanagementsystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(Car car);
    List<Car> getAllCars();
    Optional<Car> getCarById(Long carId);
    Car updateCar(Car car);

    void updateCars(Car car);
    Car getById(Long id);
}
