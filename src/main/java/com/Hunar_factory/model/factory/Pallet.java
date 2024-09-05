package com.Hunar_factory.model.factory;

import com.Hunar_factory.enums.MeterMeasure;
import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.enums.StoneSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private StoneName stoneName;
    private Integer weight;
    private StoneSize stoneSize;
    private MeterMeasure meter;
    private Long meterMount;
    private Long price;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "package_id" , nullable = false)
    private Package aPackage;
    private Date createDate;
    private Date updateDate;
}
