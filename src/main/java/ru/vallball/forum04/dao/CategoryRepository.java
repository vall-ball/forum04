package ru.vallball.forum04.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallball.forum04.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    void deleteByName(String name);

    Category findByName(String name);

}
