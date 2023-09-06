package com.sandystack.exp.model.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass
public  abstract  class BaseEntity implements Base, Serializable {

    @Override
    public abstract String getId();
}
