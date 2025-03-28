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
        for (Section s : category.getSections()) {
            list.add(SectionConverter.convertToDto(s));
        }
        dto.setSections(list);
        return dto;
    }

    public static Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        List<Section> list = new ArrayList<>();
        for (SectionDTO s : dto.getSections()) {
            list.add(SectionConverter.convertToEntity(s));
        }
        category.setSections(list);
        return category;
    }
}
