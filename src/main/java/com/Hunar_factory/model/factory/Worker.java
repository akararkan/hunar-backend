package com.Hunar_factory.model.factory;

import com.Hunar_factory.enums.WorkerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Long phoneNumber;
    private String email;
    private String address;
    private String nationality;
    private String gender;
    private WorkerType workerType;
    //    @OneToMany(mappedBy = "worker")
//    private List<Pallet> pallets;
    private Date createDate;
    private Date updateDate;


}
