package ru.vallball.forum04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL
    )
    private List<Topic> topics = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
        topic.setSection(this);
    }

    public void removeTopic(Topic topic) {
        topics.remove(topic);
        topic.setSection(null);
    }
}
