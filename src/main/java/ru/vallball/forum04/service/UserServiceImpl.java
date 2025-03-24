package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.UserRepository;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }



    @Override
    public void update(String username, User user) {
        User userForUpdate = userRepository.findByUsername(username);
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setRole(user.getRole());
        userForUpdate.setDateOfBirth(user.getDateOfBirth());
        userRepository.save(userForUpdate);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        if (user != null)
            return user;
        throw new UsernameNotFoundException("Пользователь " + username + " не найден");
    }
}
