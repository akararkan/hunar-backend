package com.Hunar_factory.model.factory;

import com.Hunar_factory.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FactoryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderCode;
    private String description;
    private String dest;
    // Owner details
    private String ownerName;
    private String ownerEmail;
    private String ownerPhoneNumber;
    private OrderStatus status;
    @OneToMany(mappedBy = "factoryOrder")
    private List<Package> packages;
    private Date createDate;
    private Date updateDate;


}
