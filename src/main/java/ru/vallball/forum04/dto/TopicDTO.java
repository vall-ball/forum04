package ru.vallball.forum04.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicDTO {

    private String name;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private UserDTO user;

    //private long countOfMessages;

    private SectionDTO section;

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

    @JsonIgnore
    public SectionDTO getSection() {
        return section;
    }

    @JsonProperty
    public void setSection(SectionDTO section) {
        this.section = section;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
