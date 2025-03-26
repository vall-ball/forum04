package ru.vallball.forum04.service;

import ru.vallball.forum04.model.Category;

import java.util.List;

public interface CategoryService {

    void save(Category category);

    List<Category> list();

    void delete(String name);

    void update(String name, Category category);

}
