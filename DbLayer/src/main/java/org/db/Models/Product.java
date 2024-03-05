package org.db.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Products")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id", updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_id")
    private int companyId;
    @Column(name = "name", updatable = true)
    private String name;
    @Column(name = "description", updatable = true)
    private String description;
    @Column(name = "price", updatable = true)
    private float price;
}
