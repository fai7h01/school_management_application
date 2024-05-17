package com.cydeo.service.impl;

import com.cydeo.entity.Lesson;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonServiceImpl extends AbstractMapService<Lesson,String>implements LessonService
    {

    @Override
    public Lesson save(Lesson lesson){
        if (lesson.getId() == null)
            lesson.setId(UUID.randomUUID().toString());

        return super.save(lesson.getId(), lesson);
        }

    @Override
    public void update(Lesson lesson)
        {

        }

    @Override
    public Lesson findById(String id)
        {
        return null;
        }

    @Override
    public List<Lesson> findAll() {
        return super.findAll();
        }

    @Override
    public void deleteById(String id)
        {

        }
    }

