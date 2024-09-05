package com.Hunar_factory.model.factory;

import com.Hunar_factory.enums.StoneName;
import com.Hunar_factory.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stone_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StoneName stoneName;
    private String description;
    private String location;
    private Type type;
    private String color;
    private Long price;
    private Date createDate;
    private Date updateDate;



}
