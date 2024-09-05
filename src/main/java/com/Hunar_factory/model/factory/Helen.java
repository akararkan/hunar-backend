package com.Hunar_factory.model.factory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "helen_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Helen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @OneToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private Worker worker;
    private Date createDate;
    private Date updateDate;

}
