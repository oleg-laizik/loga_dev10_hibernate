package loga.dev10.hibernate.properties;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;
}
