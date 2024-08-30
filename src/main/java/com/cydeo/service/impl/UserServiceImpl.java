package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import com.cydeo.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapper;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.retrieveAllUsersOrderByDesc().stream()
                .map(user -> mapper.convert(user, new UserDTO()))
                .toList();
    }

    @Override
    public List<UserDTO> listAllByRole(String roleDescription) {
        return userRepository.findAllByRoleDescriptionIgnoreCase(roleDescription).stream()
                .map(user -> mapper.convert(user, new UserDTO())).toList();
    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapper.convert(userRepository.findByUserName(username), new UserDTO());
    }

    @Override
    public UserDTO save(UserDTO user) {
        User savedUser = userRepository.save(mapper.convert(user, new User()));
        return mapper.convert(savedUser, new UserDTO());
    }

    @Override
    public UserDTO update(UserDTO user) {
        User foundUser = userRepository.findByUserName(user.getUserName());
        User convertedUser = mapper.convert(user, new User());
        convertedUser.setId(foundUser.getId());
        User savedUser = userRepository.save(convertedUser);
        return mapper.convert(savedUser, new UserDTO());
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found."));
        user.setIsDeleted(true);
        user.setUserName(user.getUserName() + "-" + user.getId());
        userRepository.save(user);
    }

    @Override
    public boolean isPasswordMatched(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getUserName().equals(email));
    }
}
