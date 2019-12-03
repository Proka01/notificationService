package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Product;
import com.raf.restdemo.dto.OrderCreateDto;
import com.raf.restdemo.dto.ProductCreateDto;
import com.raf.restdemo.dto.ProductDto;
import com.raf.restdemo.dto.ProductUpdateDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.mapper.ProductMapper;
import com.raf.restdemo.repository.ProductRepository;
import com.raf.restdemo.secutiry.service.TokenService;
import com.raf.restdemo.service.ProductService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private TokenService tokenService;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String orderDestination;

    public ProductServiceImpl(TokenService tokenService, ProductRepository productRepository, ProductMapper productMapper,
                              JmsTemplate jmsTemplate, @Value("${destination.createOrder}") String orderDestination) {

        this.tokenService = tokenService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.jmsTemplate = jmsTemplate;
        this.orderDestination = orderDestination;
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::productToProductDto);
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::productToProductDto)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id: %d not found.", id)));
    }

    @Override
    public ProductDto add(ProductCreateDto productCreateDto) {
        return productMapper.productToProductDto(productRepository
                .save(productMapper.productCreateDtoToProduct(productCreateDto)));
    }

    @Override
    public ProductDto update(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id: %d not found.", id)));
        //Set values to product
        product.setTitle(productUpdateDto.getTitle());
        product.setDescription(productUpdateDto.getDescription());
        product.setPrice(productUpdateDto.getPrice());
        //Map product to DTO and return it
        return productMapper.productToProductDto(productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void order(Long id, Integer count) {
        OrderCreateDto orderCreateDto = new OrderCreateDto(count, id);
        jmsTemplate.convertAndSend(orderDestination, messageHelper.createTextMessage(orderCreateDto));
    }
}
