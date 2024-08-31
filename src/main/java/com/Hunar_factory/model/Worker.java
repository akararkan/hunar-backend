package com.Hunar_factory.model;

import com.Hunar_factory.enums.WorkerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "worker_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private Integer phoneNumber;
    private String email;
    private String address;
    private String nationality;
    private String gender;
    private WorkerType workerType;
    @OneToMany(mappedBy = "worker")
    private List<Pallet> pallets;
    @OneToMany(mappedBy = "worker")
    private List<WorkerData> workerData;


}
