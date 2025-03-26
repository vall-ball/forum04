package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);

    List<Message> list();

    void delete(Long id);

    void update(Long id, Message message);

}
