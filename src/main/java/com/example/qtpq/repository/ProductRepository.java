package com.example.qtpq.repository;

import com.example.qtpq.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findProductByProductName(String name, Pageable pageable);
}
