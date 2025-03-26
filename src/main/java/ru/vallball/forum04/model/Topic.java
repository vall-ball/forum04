package ru.vallball.forum04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @NotNull
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name="username")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Message> messages = new ArrayList<>();

    @Column(name = "count_of_messages")
    private long countOfMessages = 0;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public Section getSection() {
        return section;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        countOfMessages++;
        message.setTopic(this);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setTopic(null);
    }

    public long getCountOfMessages() {
        return countOfMessages;
    }
}
