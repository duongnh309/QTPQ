package com.example.qtpq.repository;

import com.example.qtpq.model.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerTypeRepository extends JpaRepository<PartnerType, Long> {
}
