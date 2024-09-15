package com.example.customerms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotBlank(message = "FullName is mandatory")
    private String fullName;
    @NotBlank(message = "Age is mandatory")
    private Integer age;
    @NotBlank(message = "Pin is mandatory")
    private String pin;
    @NotBlank(message = "Balance is mandatory")
    private BigDecimal balance;
}
