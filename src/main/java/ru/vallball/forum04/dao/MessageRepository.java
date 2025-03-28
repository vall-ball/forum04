package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Message;
import ru.vallball.forum04.model.Topic;

public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteByTopicAndNumberInTopic(Topic topic, long numberInTopic);
    Message findByTopicAndNumberInTopic(Topic topic, long numberInTopic);
}
