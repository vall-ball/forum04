package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.CategoryDTO;
import ru.vallball.forum04.dto.SectionDTO;
import ru.vallball.forum04.model.Category;
import ru.vallball.forum04.model.Section;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter {

    public static CategoryDTO convertToDto(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setName(category.getName());
        List<SectionDTO> list = new ArrayList<>();
        for (Section)

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
