package com.example.qtpq.repository;

import com.example.qtpq.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM orders where  id = ?1" , nativeQuery = true)
    Orders getOrdersId(Long id);
}
