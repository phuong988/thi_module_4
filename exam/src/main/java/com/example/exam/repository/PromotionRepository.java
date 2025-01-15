package com.example.exam.repository;

import com.example.exam.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    @Query("SELECT p FROM promotion p WHERE (:discountRate IS NULL OR p.discountRate >= :discountRate) " +
            "AND (:startDate IS NULL OR p.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR p.endDate <= :endDate)")
    List<Promotion> searchPromotions(Double discountRate, LocalDate startDate, LocalDate endDate);
}
