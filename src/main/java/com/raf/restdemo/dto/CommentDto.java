package com.raf.restdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    @NotEmpty(message = "Text cant be empty")
    private String text;
    @NotEmpty(message = "user cant be empty")
    private String user;
    @Min(value = 1)
    @Max(value = 5)
    @JsonProperty("product_rating")
    private Integer productRating;
}
