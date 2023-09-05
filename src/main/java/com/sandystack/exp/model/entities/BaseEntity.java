package com.sandystack.exp.model.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public  abstract  class BaseEntity implements Base {

    @Override
    public abstract String getId();


}
