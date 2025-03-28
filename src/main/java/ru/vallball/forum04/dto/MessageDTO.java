package ru.vallball.forum04.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MessageDTO {

    private LocalDateTime dateTime;

    @NotNull
    private String text;

    private UserDTO user;

    private long numberInTopic;

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

    public long getNumberInTopic() {
        return numberInTopic;
    }

    public void setNumberInTopic(long numberInTopic) {
        this.numberInTopic = numberInTopic;
    }
}
