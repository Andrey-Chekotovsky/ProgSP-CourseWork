package org.db.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id", updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", updatable = true)
    private String username;
    @Column(name = "password", updatable = true)
    private String password;
    @Column(name = "role", updatable = true)
    private Role role;
    @Column(name = "banned", updatable = true)
    private boolean banned = false;
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private Company company;
    @ManyToMany
    @JoinTable(
            name = "user_company",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> subscriptions;
    @OneToMany
    @JoinTable(name = "orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;
    public String getStringRole() {
        return switch (role) {
            case ADMIN -> "ROLE_ADMIN";
            case CUSTOMER -> "ROLE_CUSTOMER";
            case COMPANY_AGENT -> "COMPANY_AGENT";
        };
    }
}
