package com.Hunar_factory.model.factory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Package{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    @OneToMany(mappedBy = "aPackage")
    private List<Pallet> pallets;
    private Long totalPrice;
    private Date createDate;
    private Date updateDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id" , nullable = false)
    private FactoryOrder factoryOrder;
}
