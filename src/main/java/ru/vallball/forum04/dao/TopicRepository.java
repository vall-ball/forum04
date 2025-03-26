package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    void deleteByName(String name);

    Topic findByName(String name);

}
