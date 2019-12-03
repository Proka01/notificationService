package com.raf.restdemo.service.impl;

import com.raf.restdemo.dto.OrderCreateDto;
import com.raf.restdemo.mapper.OrderMapper;
import com.raf.restdemo.repository.OrderRepository;
import com.raf.restdemo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void addOrder(OrderCreateDto orderCreateDto) {
        orderRepository.save(orderMapper.orderCreateDtoToOrder(orderCreateDto));
    }
}
