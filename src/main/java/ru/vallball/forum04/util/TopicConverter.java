package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.MessageDTO;
import ru.vallball.forum04.dto.TopicDTO;
import ru.vallball.forum04.model.Message;
import ru.vallball.forum04.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicConverter {
    public static TopicDTO convertToDto(Topic topic) {
        TopicDTO dto = new TopicDTO();
        List<MessageDTO> list = new ArrayList<>();
        for (Message m : topic.getMessages()) {
            list.add(MessageConverter.convertToDto(m));
        }
        dto.setMessages(list);
        dto.setName(topic.getName());
        dto.setDateTime(topic.getDateTime());
        return dto;
    }

    public static Topic convertToEntity(TopicDTO dto) {
        Topic topic = new Topic();
        topic.setDateTime(dto.getDateTime());
        topic.setName(dto.getName());
        List<Message> list = new ArrayList<>();
        for (MessageDTO m : dto.getMessages()) {
            list.add(MessageConverter.convertToEntity(m));
        }
        topic.setMessages(list);
        return topic;
    }
}
