package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Order;
import com.raf.restdemo.dto.OrderCreateDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order orderCreateDtoToOrder(OrderCreateDto orderCreateDto) {
        return new Order(orderCreateDto.getCount(), orderCreateDto.getProductId());
    }
}
