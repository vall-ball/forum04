package ru.vallball.forum04.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MessageDTO {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String text;

    @NotNull
    UserDTO user;

    TopicDTO topic;

    //private long numberInTopic = topic.getCountOfMessages() + 1;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

}
