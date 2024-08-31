package com.Hunar_factory.model;

import com.Hunar_factory.enums.RockName;
import com.Hunar_factory.enums.RockSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private RockName rockName;
    private RockSize rockSize;
    private Long todayGainPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

}
