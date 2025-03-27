package ru.vallball.forum04.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SectionDTO {

    @NotNull
    private String name;

    private CategoryDTO category;

    private List<TopicDTO> topics = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public CategoryDTO getCategory() {
        return category;
    }

    @JsonProperty
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }
}
