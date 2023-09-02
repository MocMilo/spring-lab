package com.sandystack.exp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Id
    @Column(name = "salesid", columnDefinition = "VARCHAR(255)")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name="employeeid")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="productid")
    private Product product;

    private Long quantitySold;

    private Long totalAmount;

    private String customerName;

    private String customerEmail;

    @Column(name ="dateofsale")
    private LocalDateTime saleDate;

}
