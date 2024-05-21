package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl extends AbstractMapService<User, String> implements UserService {

    private final CourseService courseService;
    private final LessonService lessonService;

    public UserServiceImpl(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }


    @Override
    public User save(User object) {
        return super.save(object.getUserName(), object);
    }

    @Override
    public User findById(String userName) {
        return super.findById(userName);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public void update(User object) {

        super.update(object.getUserName(), object);

    }

    @Override
    public void deleteById(String userName) {

        super.deleteById(userName);
    }

    @Override
    public List<User> findManagers() {
        return findAll().stream().filter(user -> user.getRole().getId() == 2).collect(Collectors.toList());
    }

    @Override
    public List<User> findInstructor() {
        return findAll().stream().filter(user -> user.getRole().getId() == 3L).collect(Collectors.toList());
    }

    @Override
    public boolean isPasswordMatched(String password, String confirmPassword) {
        return password.equals(confirmPassword);

    }

    @Override
    public String isEligibleToDelete(String username) {
        User user = findById(username);

        String roleName = user.getRole().getDescription();

        String result = "";

        switch (roleName) {
            case "Admin":
                if (findAll().stream().filter(u -> u.getRole().getDescription().equals("Admin")).count() == 1)
                    result = "This admin is unique in the system. Not allowed to delete";
                break;
            case "Manager":
                if (courseService.findAll().stream().anyMatch(c -> c.getCourseManager().equals(user)))
                    result = "This manager is responsible for either one or more than one courses. Not allowed to delete";
                break;
            case "Instructor":
                if (lessonService.findAll().stream().anyMatch(l -> l.getInstructor().equals(user)))
                    result = "This Instructor is responsible for either one or more than one lessons. Not allowed to delete";
                break;
        }


        return result;
    }

    @Override
    public boolean isEligibleToUpdate(String username, Long roleId) {
        User user = findById(username);

        boolean result = true;

        if (user.getRole().getId().equals(roleId)) {
            return result;
        }

        String roleName = user.getRole().getDescription();


        switch (roleName) {
            case "Admin":
                if (findAll().stream().filter(u -> u.getRole().getDescription().equals("Admin")).count() == 1)
                    result = false;
                break;
            case "Manager":
                if (courseService.findAll().stream().anyMatch(c -> c.getCourseManager().equals(user)))
                    result = false;
                break;
            case "Instructor":
                if (lessonService.findAll().stream().anyMatch(l -> l.getInstructor().equals(user)))
                    result = false;
                break;
        }

        return result;
    }
}
