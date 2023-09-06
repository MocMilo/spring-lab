package com.sandystack.exp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee extends BaseEntity implements Serializable {

    @Id
    @Column(name = "employeeid", columnDefinition = "VARCHAR(255)")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(255)")
    private String firstName;

    private String lastName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departmentid")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Sale> sales;

    private LocalDateTime hiredate;

    private String title;

    private Integer salary;

    private String address;

    private String phone;

    private String email;
}
