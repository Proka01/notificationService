package com.raf.restdemo.service.impl;

import com.raf.restdemo.domain.Product;
import com.raf.restdemo.dto.ProductDto;
import com.raf.restdemo.mapper.ProductMapper;
import com.raf.restdemo.repository.ProductRepository;
import com.raf.restdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        log.info("Getting all product. Pageable: {}.", pageable);
        return productRepository.findAll(pageable)
                .map(productMapper::productToProductDto);
    }

    @Override
    public ProductDto findById(Long id) {
        log.info("Getting product by id: {}.", id);
        return productRepository.findById(id)
                .map(productMapper::productToProductDto)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        log.info("Adding product: {}.", productDto);
        return productMapper.productToProductDto(productRepository
                .save(productMapper.productDtoToProduct(productDto)));
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return productMapper.productToProductDto(productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting product by id: {}", id);
        productRepository.deleteById(id);
    }
}
