package com.example.qtpq.repository;

import com.example.qtpq.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findSellerByUsername(String username);
}
