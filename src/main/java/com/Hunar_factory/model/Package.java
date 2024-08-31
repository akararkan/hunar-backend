package com.Hunar_factory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
