package com.example.qtpq.repository;

import com.example.qtpq.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO partner_in_menu_partners VALUES (?1 , ?2)" , nativeQuery = true)
    void addPartnerToPartnerInMenu(Long partner_in_menus_id, Long partners_id);


}
