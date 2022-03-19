package com.example.qtpq.repository;

import com.example.qtpq.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    Transactions getTransactionsByOrdersId(Long id);

    @Query(value = "SELECT * FROM transaction where  wallet_id = ?1" , nativeQuery = true)
    List<Transactions> findAllByWallet(Long id);
}
