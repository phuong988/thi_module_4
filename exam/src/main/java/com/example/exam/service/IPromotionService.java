package com.example.exam.service;

import com.example.exam.model.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionService{
    List<Promotion> getAllPromotions();
    void savePromotion(Promotion promotion);
    Promotion getPromotionById(int id);
    void deletePromotion(int id);
    List<Promotion> searchPromotions(Double discountRate, LocalDate startDate, LocalDate endDate);

}
