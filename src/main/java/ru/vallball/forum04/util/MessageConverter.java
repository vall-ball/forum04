package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.MessageDTO;
import ru.vallball.forum04.model.Message;

public class MessageConverter {

    public static MessageDTO convertToDto(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setText(message.getText());
        dto.setDateTime(message.getDateTime());
        dto.setUser(UserConverter.convertToDto(message.getUser()));
        dto.setNumberInTopic(message.getNumberInTopic());
        return dto;
    }

    public static Message convertToEntity(MessageDTO dto) {
        Message message = new Message();
        message.setDateTime(dto.getDateTime());
        message.setText(dto.getText());
        message.setUser(UserConverter.convertToEntity(dto.getUser()));
        return message;
    }
}
