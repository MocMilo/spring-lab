package com.sandystack.exp.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department extends BaseEntity {

    @Id
    @Column(name = "departmentid", columnDefinition = "VARCHAR(255)")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private String id;

    @Column(name = "departmentname")
    private String name;

}
