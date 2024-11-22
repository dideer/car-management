package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.Sale;
import com.didier.carmanagementsystem.model.User;

import java.util.List;

public interface SaleService  {

    Sale save(Sale sale);


    Sale getSale(Long id);

    List<Sale> getSalesByUserId(Long userId);

    List<Sale> getSalesByDay();   // New method
    List<Sale> getSalesByWeek();  // New method
    List<Sale> getSalesByMonth(); // New method
    List<Sale> getSalesByYear();  // New method

    List<Sale> getSalesByDayForUser(Long userId);
    List<Sale> getSalesByWeekForUser(Long userId);
    List<Sale> getSalesByMonthForUser(Long userId);
    List<Sale> getSalesByYearForUser(Long userId);

}
