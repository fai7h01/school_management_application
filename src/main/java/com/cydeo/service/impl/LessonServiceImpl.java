package com.cydeo.service.impl;

import com.cydeo.entity.Lesson;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl extends AbstractMapService<Lesson, Long> implements LessonService {

    @Override
    public Lesson save(Lesson lesson) {
        if (lesson.getId() == null)

            lesson.setId(UUID.randomUUID().getLeastSignificantBits());

        return super.save(lesson.getId(), lesson);
    }

@Override
public Lesson findById(Long aLong)
    {
    return super.findById(aLong);
    }

@Override
    public void update(Lesson lesson)
    {
    super.update(lesson.getId(), lesson);

    }


    @Override
    public List<Lesson> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong)
    {
    super.deleteById(aLong);

    }



    @Override
    public List<Lesson> findAllLessonByCourseId(Long courseId) {
        return findAll().stream()
                .filter(lesson -> lesson.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());
    }
}

