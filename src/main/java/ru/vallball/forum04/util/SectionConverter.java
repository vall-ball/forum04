package ru.vallball.forum04.util;

import ru.vallball.forum04.dto.SectionDTO;
import ru.vallball.forum04.dto.TopicDTO;
import ru.vallball.forum04.model.Section;
import ru.vallball.forum04.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class SectionConverter {
    public static SectionDTO convertToDto(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setName(section.getName());
        List<TopicDTO> list = new ArrayList<>();
        for (Topic t : section.getTopics()) {
            list.add(TopicConverter.convertToDto(t));
        }
        dto.setTopics(list);
        return dto;
    }

    public static Section convertToEntity(SectionDTO dto) {
        Section section = new Section();
        section.setName(dto.getName());
        List<Topic> list = new ArrayList<>();
        for (TopicDTO t : dto.getTopics()) {
            list.add(TopicConverter.convertToEntity(t));
        }
        section.setTopics(list);
        return section;
    }
}
