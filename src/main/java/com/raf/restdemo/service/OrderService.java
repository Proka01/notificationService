package com.raf.restdemo.service;

import com.raf.restdemo.dto.OrderCreateDto;

public interface OrderService {

    void addOrder(OrderCreateDto orderCreateDto);
}
