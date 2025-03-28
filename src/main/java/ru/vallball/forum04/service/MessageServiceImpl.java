package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.MessageRepository;
import ru.vallball.forum04.dao.TopicRepository;
import ru.vallball.forum04.dao.UserRepository;
import ru.vallball.forum04.model.Message;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.Topic;
import ru.vallball.forum04.model.User;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Message message, String topicName, String username) {
        message.setUser(userRepository.findByUsername(username));
        message.setTopic(topicRepository.findByName(topicName));
        messageRepository.save(message);
    }

    @Override
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @Override
    public void delete(String topicName, long numberInTopic, User principal) throws Exception {
        Topic topic = topicRepository.findByName(topicName);
        Message message = messageRepository.findByTopicAndNumberInTopic(topic, numberInTopic);
        if (principal.getRole().equals(Role.ROLE_ADMIN) || principal.getRole().equals(Role.ROLE_MODERATOR) || message.getUser().getUsername().equals(principal.getUsername())) {
            messageRepository.delete(message);
        } else {
            throw new Exception("You don't have rights!");
        }
    }

    @Override
    public void update(String topicName, long numberInTopic, Message message) throws Exception {
        Message messageForUpdate = messageRepository.findByTopicAndNumberInTopic(topicRepository.findByName(topicName), numberInTopic);
        if (message.getUser().getRole().equals(Role.ROLE_ADMIN) || message.getUser().getRole().equals(Role.ROLE_MODERATOR) || message.getUser().getUsername().equals(messageForUpdate.getUser().getUsername())) {
            messageForUpdate.setTopic(message.getTopic());
            messageForUpdate.setText(message.getText());
            messageForUpdate.setUser(message.getUser());
            messageForUpdate.setDateTime(message.getDateTime());
            messageRepository.save(messageForUpdate);
        } else {
            throw new Exception("You don't have rights!");
        }
    }
}
