package ru.vallball.forum04.dto;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {

    @NotNull
    private String name;

    private List<SectionDTO> sections = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
