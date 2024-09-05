package com.Hunar_factory.model.quarry;

import com.Hunar_factory.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "qyarry_order_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuarryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderCode;
    private String dest;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private OrderStatus status;
    @OneToMany(mappedBy = "quarryOrder")
    private List<QuarryPackage> quarryPackages;
    private Integer quantity;
    private Date createDate;
    private Date updateDate;
}
