package ru.vallball.forum04.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.vallball.forum04.dto.UserDTO;
import ru.vallball.forum04.model.Role;
import ru.vallball.forum04.model.User;
import ru.vallball.forum04.service.UserService;
import ru.vallball.forum04.util.UserConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserDTO> list() {
        List<UserDTO> list = new ArrayList<>();
        for (User u : userService.list()) {
            list.add(UserConverter.convertToDto(u));
        }
        return list;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO dto) throws Exception {
        User user = UserConverter.convertToEntity(dto);
        userService.save(user.toUser(passwordEncoder));
        return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable(value = "username") String name) {
        User user = userService.findByUsername(name);
        if (user == null) {
            return new ResponseEntity<>("The user is not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserConverter.convertToDto(user));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable(value = "email") String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("The user is not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserConverter.convertToDto(user));
    }

    @GetMapping("/role/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable(value = "role") String role) {
        List<User> users = userService.findByRole(Role.valueOf("ROLE_" + role));
        List<UserDTO> answer = new ArrayList<>();
        for (User u : users) {
            answer.add(UserConverter.convertToDto(u));
        }
        return answer;
    }

    @PutMapping("/change_user/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "username") String username, @Valid @RequestBody UserDTO dto) {
        try {
            userService.update(username, UserConverter.convertToEntity(dto).toUser(passwordEncoder));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User is updated successfully", HttpStatus.ACCEPTED);
    }

    @PostMapping("/for_users")
    public ResponseEntity<Object> createUserByOwn(@Valid @RequestBody UserDTO dto) throws Exception {
        dto.setRole(Role.ROLE_USER);
        userService.save(UserConverter.convertToEntity(dto).toUser(passwordEncoder));
        return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/for_users")
    public ResponseEntity<Object> updateUserByHimself(@AuthenticationPrincipal User principal, @RequestBody UserDTO dto) {
        dto.setRole(Role.ROLE_USER);
        userService.update(principal.getUsername(), UserConverter.convertToEntity(dto).toUser(passwordEncoder));
        return new ResponseEntity<>("User is updated successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> delete(@PathVariable(value = "username") String name) {
        try {
            userService.delete(name);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("The user not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The is deleted successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/for_users")
    public ResponseEntity<Object> delete(@AuthenticationPrincipal User principal) {
        try {
            userService.delete(principal.getUsername());
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("The user not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The user is deleted successfully", HttpStatus.ACCEPTED);
    }

}
