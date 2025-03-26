package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Topic;

import java.util.List;

public interface TopicService {

    void save(Topic topic);

    List<Topic> list();

    void delete(String name);

    void update(String name, Topic topic);

}
