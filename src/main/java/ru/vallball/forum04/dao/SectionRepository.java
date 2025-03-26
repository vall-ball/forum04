package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

    void deleteByName(String name);

    Section findByName(String name);

}
