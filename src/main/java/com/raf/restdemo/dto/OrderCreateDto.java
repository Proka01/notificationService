package com.raf.restdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCreateDto {

    private Integer count;
    @JsonProperty("product_id")
    private Long productId;

    public OrderCreateDto() {

    }

    public OrderCreateDto(Integer count, Long productId) {
        this.count = count;
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}