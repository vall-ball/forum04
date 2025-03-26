package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.CategoryRepository;
import ru.vallball.forum04.model.Category;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(String name) {
        categoryRepository.deleteByName(name);
    }

    @Override
    public void update(String name, Category category) {
        Category categoryForUpdate = categoryRepository.findByName(name);
        categoryForUpdate.setName(category.getName());
        categoryForUpdate.setSections(category.getSections());
        categoryRepository.save(categoryForUpdate);
    }
}
