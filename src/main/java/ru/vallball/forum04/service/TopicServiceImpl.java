package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.SectionRepository;
import ru.vallball.forum04.dao.TopicRepository;
import ru.vallball.forum04.dao.UserRepository;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.Topic;
import ru.vallball.forum04.model.User;

import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Topic topic, String sectionName, String username) {
        topic.setSection(sectionRepository.findByName(sectionName));
        topic.setUser(userRepository.findByUsername(username));
        topicRepository.save(topic);
    }

    @Override
    public List<Topic> list() {
        return topicRepository.findAll();
    }

    @Override
    public void delete(String name) {
        topicRepository.deleteByName(name);
    }

    @Override
    public void update(String name, Topic topic) throws Exception {
        Topic topicForUpdate = topicRepository.findByName(name);
        if (topic.getUser().getRole().equals(Role.ROLE_ADMIN) || topic.getUser().getRole().equals(Role.ROLE_MODERATOR) || topic.getUser().getUsername().equals(topicForUpdate.getUser().getUsername())) {
            topicForUpdate.setMessages(topic.getMessages());
            topicForUpdate.setName(topic.getName());
            topicRepository.save(topicForUpdate);
        } else {
            throw new Exception("You don't have rights!");
        }
    }
}
