package com.didier.carmanagementsystem.service.imp;

import com.didier.carmanagementsystem.model.Car;
import com.didier.carmanagementsystem.repository.CarRepository;
import com.didier.carmanagementsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarRepository carRepository;


    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long carId) {
        return carRepository.findById(carId);
    }

    @Override
    public Car updateCar(Car car) {


        return carRepository.save(car);
    }

    @Override
    public void updateCars(Car car) {
        if (car.getCarId() != null && carRepository.existsById(car.getCarId())) {
            carRepository.save(car);
        }
    }

    @Override
    public Car getById(Long id) {
        return carRepository.findById(id).get();
    }
}
