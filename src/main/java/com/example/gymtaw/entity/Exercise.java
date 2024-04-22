package com.example.gymtaw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @Column(name = "video", nullable = false, length = 250)
    private String video;

    @Lob
    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_idtype", nullable = false)
    private Type typeIdtype;

}