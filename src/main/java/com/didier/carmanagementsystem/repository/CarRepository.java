package com.didier.carmanagementsystem.repository;

import com.didier.carmanagementsystem.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
