package ru.vallball.forum04.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicDTO {

    @NotNull
    private String name;

    private LocalDateTime dateTime;

    private UserDTO user;

    private long countOfMessages;

    private List<MessageDTO> messages = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public long getCountOfMessages() {
        return countOfMessages;
    }

    public void setCountOfMessages(long countOfMessages) {
        this.countOfMessages = countOfMessages;
    }
}
