package com.Hunar_factory.model;

import com.Hunar_factory.enums.MeterMeasure;
import com.Hunar_factory.enums.RockName;
import com.Hunar_factory.enums.RockSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pallet_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RockName rockName;
    private Integer weight;
    private RockSize rockSize;
    private MeterMeasure meter;
    private Long meterMount;
    private Long price;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @OneToOne(mappedBy = "worker", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Helen helen;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "package_id" , nullable = false)
    private Package aPackage;
}
