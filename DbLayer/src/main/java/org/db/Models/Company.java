package org.db.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Companies")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @Column(name = "company_id", updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", updatable = true)
    private String name;
    @Column(name = "description", updatable = true)
    private String description;
    @ManyToMany(mappedBy = "subscriptions")
    private List<User> subscribers;
}
