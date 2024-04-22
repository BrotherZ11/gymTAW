package com.example.gymtaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "session")
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

}