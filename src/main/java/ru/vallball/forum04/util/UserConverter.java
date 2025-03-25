package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.UserDTO;
import ru.vallball.forum04.model.User;

public class UserConverter {

    public static UserDTO convertToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setDateOfBirth(user.getDateOfBirth());
        return dto;
    }

    public static User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        return user;
    }
}
