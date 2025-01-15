package com.example.exam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT")
    private int id;

    @Column(name = "title",  columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày bắt đầu khuyến mãi không được để trống")
    @Future(message = "Ngày bắt đầu phải lớn hơn thời gian hiện tại")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày kết thúc khuyến mãi không được để trống")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Mức giảm giá không được để trống")
    @Min(value = 10000, message = "Mức giảm giá phải lớn hơn 10000 VNĐ")
    @Column(name = "discount_rate", columnDefinition = "DOUBLE", nullable = false)
    private double discountRate;

    @NotNull(message = "Chi tiết không được để trống")
    @Column(name = "detail",  columnDefinition = "VARCHAR(255)", nullable = false)
    private String detail;
    @AssertTrue(message = "Ngày kết thúc phải lớn hơn ngày bắt đầu ít nhất 1 ngày")
    private boolean isEndDateValid() {
        return endDate != null && startDate != null && endDate.isAfter(startDate.plusDays(1));
    }
}
