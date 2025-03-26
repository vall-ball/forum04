package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.MessageRepository;
import ru.vallball.forum04.model.Message;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void update(Long id, Message message) {
        Message messageForUpdate = messageRepository.findById(id).get();
        messageForUpdate.setTopic(message.getTopic());
        messageForUpdate.setText(message.getText());
        messageForUpdate.setUser(message.getUser());
        messageForUpdate.setDateTime(message.getDateTime());
        messageRepository.save(messageForUpdate);
    }
}
