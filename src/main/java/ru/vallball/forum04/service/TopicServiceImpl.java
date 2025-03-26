package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.TopicRepository;
import ru.vallball.forum04.model.Topic;

import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public void save(Topic topic) {
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
    public void update(String name, Topic topic) {
        Topic topicForUpdate = topicRepository.findByName(name);
        topicForUpdate.setSection(topic.getSection());
        topicForUpdate.setMessages(topic.getMessages());
        topicForUpdate.setName(topic.getName());
        topicForUpdate.setUser(topic.getUser());
        topicForUpdate.setDateTime(topic.getDateTime());
        topicRepository.save(topicForUpdate);
    }
}
