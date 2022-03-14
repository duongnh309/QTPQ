package com.example.qtpq.repository;

import com.example.qtpq.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product_menu VALUES (?1 , ?2)" , nativeQuery = true)
    void addProductToMenu(Long productId ,Long menuId);
}
