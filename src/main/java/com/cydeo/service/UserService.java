package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    List<UserDTO> listAllByRole(String roleDescription);

    UserDTO findByUsername(String username);

    UserDTO save(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(Long id);

//    boolean isEligibleToUpdate(Long userId, Long roleId);
//
//    String isEligibleToDelete(Long id);


}
