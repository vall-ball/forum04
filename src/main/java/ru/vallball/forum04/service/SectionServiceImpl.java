package ru.vallball.forum04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vallball.forum04.dao.CategoryRepository;
import ru.vallball.forum04.dao.SectionRepository;
import ru.vallball.forum04.model.Section;

import java.util.List;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void save(Section section, String categoryName) {
        section.setCategory(categoryRepository.findByName(categoryName));
        sectionRepository.save(section);
    }

    @Override
    public List<Section> list() {
        return sectionRepository.findAll();
    }

    @Override
    public void delete(String name) {
        sectionRepository.deleteByName(name);
    }

    @Override
    public void update(String name, Section section) {
        Section sectionForUpdate = sectionRepository.findByName(name);
        sectionForUpdate.setName(section.getName());
        sectionForUpdate.setTopics(section.getTopics());
        sectionRepository.save(sectionForUpdate);
    }
}
