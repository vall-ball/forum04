package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    void deleteByUsername(String username);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRole(Role role);

}
