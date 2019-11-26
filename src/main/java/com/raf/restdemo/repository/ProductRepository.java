package com.raf.restdemo.repository;

import com.raf.restdemo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    void deleteById(Long id);
}
