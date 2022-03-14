package com.example.qtpq.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerInMenu {

    @EmbeddedId
    private PartnerInMenuId id = new PartnerInMenuId();
    @JsonIgnore
    @ManyToOne
    @MapsId("menuId")
    private Menu menu;

    @ManyToOne
    @MapsId("partnerId")
    private Partner partner;

//    @Transient
//    private Long partnerID;


    private LocalDate startDate;
    private LocalDate endDate;


    @Embeddable
    public static class PartnerInMenuId implements Serializable {
        private Long partnerId;
        private Long menuId;

        public PartnerInMenuId(Long partnerId, Long menuId) {
            super();
            this.partnerId = partnerId;
            this.menuId = menuId;
        }

        public PartnerInMenuId() {
        }

        public Long getUserId() {
            return partnerId;
        }

        public void setUserId(Long userId) {
            this.partnerId = userId;
        }

        public Long getOrderId() {
            return menuId;
        }

        public void setOrderId(Long orderId) {
            this.menuId = menuId;
        }

    }

}
