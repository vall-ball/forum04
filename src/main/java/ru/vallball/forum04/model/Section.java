package ru.vallball.forum04.model;

import jakarta.persistence.*;


@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
