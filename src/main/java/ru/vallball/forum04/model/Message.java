package ru.vallball.forum04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull
    private String text;

    @NotNull
    @ManyToOne
    @JoinColumn(name="username")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    Topic topic;

    @Column(name = "number_in_topic")
    private long numberInTopic = topic.getCountOfMessages() + 1;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public long getNumberInTopic() {
        return numberInTopic;
    }
}
