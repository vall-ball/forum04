package ru.vallball.forum04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vallball.forum04.dto.CategoryDTO;
import ru.vallball.forum04.dto.MessageDTO;
import ru.vallball.forum04.dto.SectionDTO;
import ru.vallball.forum04.dto.TopicDTO;
import ru.vallball.forum04.model.Category;
import ru.vallball.forum04.model.User;
import ru.vallball.forum04.service.CategoryService;
import ru.vallball.forum04.service.MessageService;
import ru.vallball.forum04.service.SectionService;
import ru.vallball.forum04.service.TopicService;
import ru.vallball.forum04.util.*;

import java.time.LocalDateTime;
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
    @PostMapping("section/{categoryName}")
    public ResponseEntity<Object> createSection(@RequestBody SectionDTO sectionDTO, @PathVariable(value = "categoryName") String categoryName) {
        sectionService.save(SectionConverter.convertToEntity(sectionDTO), categoryName);
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

    //work with topics
    @PostMapping("topic/{sectionName}")
    public ResponseEntity<Object> createTopic(@AuthenticationPrincipal User principal, @RequestBody TopicDTO topicDTO, @PathVariable(value = "sectionName") String sectionName) {
        topicDTO.setUser(UserConverter.convertToDto(principal));
        topicDTO.setDateTime(LocalDateTime.now());
        topicService.save(TopicConverter.convertToEntity(topicDTO), sectionName, principal.getUsername());
        return new ResponseEntity<>("The topic is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("topic/{name}")
    public ResponseEntity<Object> deleteTopic(@PathVariable(value = "name") String name) {
        try {
            topicService.delete(name);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("The topic not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The topic is deleted successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("topic/{name}")
    public ResponseEntity<Object> updateTopic(@AuthenticationPrincipal User principal, @PathVariable(value = "name") String name, @RequestBody TopicDTO topicDTO) throws Exception {
        topicDTO.setUser(UserConverter.convertToDto(principal));
        topicService.update(name, TopicConverter.convertToEntity(topicDTO));
        return new ResponseEntity<>("The topic is updated successfully", HttpStatus.ACCEPTED);
    }

    //work with topics
    @PostMapping("message/{topicName}")
    public ResponseEntity<Object> createMessage(@AuthenticationPrincipal User principal, @RequestBody MessageDTO messageDTO, @PathVariable(value = "topicName") String topicName) {
        messageDTO.setUser(UserConverter.convertToDto(principal));
        messageDTO.setDateTime(LocalDateTime.now());
        messageService.save(MessageConverter.convertToEntity(messageDTO), topicName, principal.getUsername());
        return new ResponseEntity<>("The message is created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("message/{topicName}/{numberInTopic}")
    public ResponseEntity<Object> deleteMessage(@AuthenticationPrincipal User principal, @PathVariable(value = "topicName") String topicName, @PathVariable(value = "numberInTopic") long numberInTopic) {
        try {
            messageService.delete(topicName, numberInTopic, principal);
        } catch (Exception e) {
            return new ResponseEntity<>("The message not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("The message is deleted successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("message/{topicName}/{numberInTopic}")
    public ResponseEntity<Object> updateMessage(@AuthenticationPrincipal User principal, @PathVariable(value = "topicName") String topicName, @PathVariable(value = "numberInTopic") long numberInTopic, @RequestBody MessageDTO messageDTO) throws Exception {
        messageDTO.setUser(UserConverter.convertToDto(principal));
        messageService.update(topicName, numberInTopic, MessageConverter.convertToEntity(messageDTO));
        return new ResponseEntity<>("The message is updated successfully", HttpStatus.ACCEPTED);
    }

}
