package com.raf.restdemo.listener;

import com.raf.restdemo.dto.OrderCreateDto;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.service.OrderService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class OrderListener {

    private MessageHelper messageHelper;
    private OrderService orderService;

    public OrderListener(MessageHelper messageHelper, OrderService orderService) {
        this.messageHelper = messageHelper;
        this.orderService = orderService;
    }

    @JmsListener(destination = "${destination.createOrder}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        OrderCreateDto orderCreateDto = messageHelper.getMessage(message, OrderCreateDto.class);
        orderService.addOrder(orderCreateDto);
    }
}


