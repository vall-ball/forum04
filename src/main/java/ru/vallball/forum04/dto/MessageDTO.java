package ru.vallball.forum04.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MessageDTO {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private String text;

    @NotNull
    private UserDTO user;

    private TopicDTO topic;

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

    @JsonIgnore
    public TopicDTO getTopic() {
        return topic;
    }

    @JsonProperty
    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

}
