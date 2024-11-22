package com.didier.carmanagementsystem.service.imp;

import com.didier.carmanagementsystem.model.Car;
import com.didier.carmanagementsystem.model.Sale;
import com.didier.carmanagementsystem.model.User;
import com.didier.carmanagementsystem.repository.CarRepository;
import com.didier.carmanagementsystem.repository.SaleRepository;
import com.didier.carmanagementsystem.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImp implements SaleService {


    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Sale save(Sale sale) {
        Car car = sale.getCar();
        int updatedQuantity = car.getQuantity() - sale.getQuantity();
        int totalPrice = car.getPrice() * updatedQuantity;
        car.setQuantity(updatedQuantity);
        car.setTotalPrice(totalPrice);



        // Save the car with updated quantity
        carRepository.save(car);

        // Save the sale
        return saleRepository.save(sale);
    }

    @Override
    public Sale getSale(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public List<Sale> getSalesByUserId(Long userId) {

        return saleRepository.findByUser_UserId(userId);
    }

    @Override
    public List<Sale> getSalesByDay() {
        LocalDate today = LocalDate.now();
        return saleRepository.findBySaleDate(today);
    }

    @Override
    public List<Sale> getSalesByWeek() {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.next(java.time.DayOfWeek.SUNDAY));
        return saleRepository.findBySaleDateBetween(startOfWeek, endOfWeek);
    }

    @Override
    public List<Sale> getSalesByMonth() {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return saleRepository.findBySaleDateBetween(startOfMonth, endOfMonth);
    }

    @Override
    public List<Sale> getSalesByYear() {
        LocalDate startOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        LocalDate endOfYear = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        return saleRepository.findBySaleDateBetween(startOfYear, endOfYear);
    }

    @Override
    public List<Sale> getSalesByDayForUser(Long userId) {
        LocalDate today = LocalDate.now();
        return saleRepository.findBySaleDateAndUser_UserId(today, userId);
    }

    @Override
    public List<Sale> getSalesByWeekForUser(Long userId) {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.next(java.time.DayOfWeek.SUNDAY));
        return saleRepository.findBySaleDateBetweenAndUser_UserId(startOfWeek, endOfWeek, userId);
    }

    @Override
    public List<Sale> getSalesByMonthForUser(Long userId) {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return saleRepository.findBySaleDateBetweenAndUser_UserId(startOfMonth, endOfMonth, userId);

    }

    @Override
    public List<Sale> getSalesByYearForUser(Long userId) {
        LocalDate startOfYear = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        LocalDate endOfYear = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        return saleRepository.findBySaleDateBetweenAndUser_UserId(startOfYear, endOfYear, userId);

    }


}
