package com.cydeo.converter;

import com.cydeo.entity.User;
import com.cydeo.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter  implements Converter<String, User>
    {

    UserService userService;

    public UserConverter(UserService userService)
        {
        this.userService = userService;
        }

    @Override
    public User convert(String source)
        {
        if (source == null || source.equals(""))
            return null;
        return userService.findById(source);
        }
    }
