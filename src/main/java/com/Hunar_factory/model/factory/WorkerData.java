package com.Hunar_factory.model.factory;

import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.enums.StoneSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "worker_data_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer todayCutWeight;
    private StoneName stoneName;
    private StoneSize stoneSize;
    private Long todayGainPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;
    private Date createDate;
    private Date updateDate;

}
