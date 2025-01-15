package com.example.exam.service.impl;

import com.example.exam.model.Promotion;
import com.example.exam.repository.PromotionRepository;
import com.example.exam.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private PromotionRepository promotionRepository;


    @Override
    public List getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public void savePromotion(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(int id) {
        return null;
    }

    @Override
    public void deletePromotion(int id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> searchPromotions(Double discountRate, LocalDate startDate, LocalDate endDate) {
        return promotionRepository.searchPromotions(discountRate, startDate, endDate);
    }

}
