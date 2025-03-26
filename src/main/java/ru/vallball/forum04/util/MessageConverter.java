package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.CategoryDTO;
import ru.vallball.forum04.dto.MessageDTO;
import ru.vallball.forum04.dto.SectionDTO;
import ru.vallball.forum04.model.Message;
import ru.vallball.forum04.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class MessageConverter {

    public static MessageDTO convertToDto(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setText(message.getText());
        dto.setTopic(TopicConverter.convertToDto(message.getTopic()));
        return dto;
    }

    public static User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        return user;
    }
}
