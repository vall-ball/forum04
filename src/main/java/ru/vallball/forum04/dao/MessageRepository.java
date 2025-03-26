package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
