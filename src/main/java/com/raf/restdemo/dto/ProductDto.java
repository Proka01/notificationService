package com.raf.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    @NotEmpty(message = "Title cant be empty")
    private String title;
    private String description;
    @Min(value = 0, message = "The value must be positive")
    private BigDecimal price;
}
