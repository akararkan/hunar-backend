package com.Hunar_factory.model.quarry;

import com.Hunar_factory.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quarry_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Quarry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    private Type type;
    private boolean availability;
    @OneToMany(mappedBy = "quarry")
    private List<QuarryWorker> quarryWorker;
    private Date createDate;
    private Date updateDate;

}
