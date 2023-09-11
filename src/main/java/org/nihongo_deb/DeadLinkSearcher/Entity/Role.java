package org.nihongo_deb.DeadLinkSearcher.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String name;

    public enum RoleType {
        ROLE_USER, ROLE_ADMIN;
    }
}
