package com.example.qtpq.repository;

import com.example.qtpq.model.PartnerInMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PartnerInMenuRepository extends JpaRepository<PartnerInMenu, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO partner_in_menu VALUES (?1 , ?2)" , nativeQuery = true)
    void addMenuToPartner(Long menuId ,Long partnerId);

    @Query(value = "SELECT CASE WHEN count(menu_id) > 0 THEN 1 ELSE 0 END " +
            "checkExist FROM partner_in_menu where menu_id =?1 and partner_id =?2 limit 1" , nativeQuery = true)
    int isInPartner(Long menuId , Long partnerId);


}
