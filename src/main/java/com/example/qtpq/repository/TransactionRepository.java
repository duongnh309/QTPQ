package com.example.qtpq.repository;

import com.example.qtpq.model.Transaction;
import com.example.qtpq.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction getTransactionsByOrdersId(Long id);

    @Query(value = "SELECT * FROM qtpq.transaction where  wallet_id = ?1" , nativeQuery = true)
    List<Transaction> findAllByWallet(Long id);
}
