package org.nihongo_deb.DeadLinkSearcher.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_telegram_id")
    private Long telegramId;

    @Column(name = "user_created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "user-role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
