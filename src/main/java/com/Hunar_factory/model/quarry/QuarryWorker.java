package com.Hunar_factory.model.quarry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "quarry_worker_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuarryWorker  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "quarry_id" , nullable = false)
    private Quarry quarry;
    private Date createDate;
    private Date updateDate;

}
