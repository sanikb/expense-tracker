package com.expense.jforce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private int productId;

    @NotBlank(message = "productname is required")
    private String productName;

    @NotNull(message = "amount is required")
    @Positive(message = "amount must be greater than 0")
    private Long productAmount;


    @NotBlank(message = "description is required")
    private String productDescription;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

}
