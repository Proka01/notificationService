package com.raf.restdemo.service;

import com.raf.restdemo.dto.ProductCreateDto;
import com.raf.restdemo.dto.ProductDto;
import com.raf.restdemo.dto.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> findAll(Pageable pageable);

    ProductDto findById(Long id);

    ProductDto add(ProductCreateDto productCreateDto);

    ProductDto update(Long id, ProductUpdateDto productUpdateDto);

    void deleteById(Long id);

}
