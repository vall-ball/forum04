package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Topic;
import ru.vallball.forum04.model.User;

import java.util.List;

public interface TopicService {

    void save(Topic topic, String sectionName, String username);

    List<Topic> list();

    void delete(String name);

    void update(String name, Topic topic) throws Exception;

}
