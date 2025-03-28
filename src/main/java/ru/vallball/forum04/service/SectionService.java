package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Section;

import java.util.List;

public interface SectionService {

    void save(Section section, String categoryName);

    List<Section> list();

    void delete(String name);

    void update(String name, Section section);

}
