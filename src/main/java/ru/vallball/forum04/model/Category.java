package ru.vallball.forum04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<Section> sections = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        sections.add(section);
        section.setCategory(this);
    }

    public void removeSection(Section section) {
        sections.remove(section);
        section.setCategory(null);
    }

    public Long getId() {
        return id;
    }
}
