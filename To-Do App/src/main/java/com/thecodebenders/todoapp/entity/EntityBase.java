package com.thecodebenders.todoapp.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class EntityBase {

    @Column(name = "create_date")
}
