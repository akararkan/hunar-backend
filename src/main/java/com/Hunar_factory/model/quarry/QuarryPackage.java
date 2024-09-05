package com.Hunar_factory.model.quarry;

import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.enums.TripperType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "quarry_package_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuarryPackage  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StoneName name;
    private String description;
    private String size;
    private TripperType tripperType;
    private Long packageWeight;
    private Long priceInQuarry;
    private Date createDate;
    private Date updateDate;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "quarry_order_id", nullable = false)
    private QuarryOrder quarryOrder;

}
