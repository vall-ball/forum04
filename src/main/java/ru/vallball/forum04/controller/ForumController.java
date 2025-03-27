package ru.vallball.forum04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vallball.forum04.dto.CategoryDTO;
import ru.vallball.forum04.dto.SectionDTO;
import ru.vallball.forum04.model.Category;
import ru.vallball.forum04.service.CategoryService;
import ru.vallball.forum04.service.MessageService;
import ru.vallball.forum04.service.SectionService;
import ru.vallball.forum04.service.TopicService;
import ru.vallball.forum04.util.CategoryConverter;
import ru.vallball.forum04.util.SectionConverter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/forum")
public class ForumController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SectionService sectionService;

    @Autowired
    TopicService topicService;

    @Autowired
    MessageService messageService;

    @GetMapping
    public List<CategoryDTO> list() {
        List<CategoryDTO> list = new ArrayList<>();
        for (Category c : categoryService.list()) {
            list.add(CategoryConverter.convertToDto(c));
        }
        return list;
    }

    //work with categories

    @PostMapping("category")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(CategoryConverter.convertToEntity(categoryDTO));
        return new ResponseEntity<>("The category is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("category/{name}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "name") String name) {
        try {
            categoryService.delete(name);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("The category not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The category is deleted successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("category/{name}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "name") String name, @RequestBody CategoryDTO categoryDTO) {
        categoryService.update(name, CategoryConverter.convertToEntity(categoryDTO));
        return new ResponseEntity<>("The category is updated successfully", HttpStatus.ACCEPTED);
    }

    //work with sections
    @PostMapping("section")
    public ResponseEntity<Object> createSection(@RequestBody SectionDTO sectionDTO) {
        System.out.println("sectionService.save(SectionConverter.convertToEntity(sectionDTO));");
        System.out.println(sectionDTO.getCategory());
        sectionService.save(SectionConverter.convertToEntity(sectionDTO));

        return new ResponseEntity<>("The section is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("section/{name}")
    public ResponseEntity<Object> deleteSection(@PathVariable(value = "name") String name) {
        try {
            sectionService.delete(name);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("The section not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The section is deleted successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("section/{name}")
    public ResponseEntity<Object> updateSection(@PathVariable(value = "name") String name, @RequestBody SectionDTO sectionDTO) {
        sectionService.update(name, SectionConverter.convertToEntity(sectionDTO));
        return new ResponseEntity<>("The section is updated successfully", HttpStatus.ACCEPTED);
    }

}
