package com.cydeo.service;

import com.cydeo.entity.User;

import java.util.List;

public interface UserService extends CrudService <User,String> {
    List<User> findManagers();
    List<User> findInstructor();

}
