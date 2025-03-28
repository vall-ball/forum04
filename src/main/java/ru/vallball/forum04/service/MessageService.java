package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Message;
import ru.vallball.forum04.model.User;

import java.util.List;

public interface MessageService {

    void save(Message message, String topicName, String username);

    List<Message> list();

    void delete(String topicName, long numberInTopic, User principal) throws Exception;

    void update(String topicName, long numberInTopic, Message message) throws Exception;

}
