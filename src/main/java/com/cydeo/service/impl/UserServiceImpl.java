package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl extends AbstractMapService<User,String> implements UserService {


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
        return findAll().stream().filter(user -> user.getRole().getId()==2).collect(Collectors.toList());
    }

@Override
public List<User> findInstructor()
    {
    return findAll().stream().filter(user -> user.getRole().getId()==3L).collect(Collectors.toList());
    }
}
