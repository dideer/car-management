package com.didier.carmanagementsystem.repository;

import com.didier.carmanagementsystem.model.Sale;
import com.didier.carmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByUser_UserId(Long userId);
    List<Sale> findBySaleDate(LocalDate saleDate);
    List<Sale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
    List<Sale> findBySaleDateBetweenAndUser_UserId(LocalDate startDate, LocalDate endDate, Long userId);
    List<Sale> findBySaleDateAndUser_UserId(LocalDate saleDate, Long userId);


    
}
