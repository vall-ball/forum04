package ru.vallball.forum04.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    List<User> list();

    void delete(String username);

    void update(String username, User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByRole(Role role);

}
