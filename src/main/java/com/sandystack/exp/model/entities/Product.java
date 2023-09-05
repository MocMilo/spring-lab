package com.sandystack.exp.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @Column(name = "productid", columnDefinition = "VARCHAR(255)")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Sale> sales;

    private String productName;
    private Long stockQuantity;

    private Long unitPrice;
    private String category;
    private String supplier;
    private String description;


}
