package com.Hunar_factory.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
