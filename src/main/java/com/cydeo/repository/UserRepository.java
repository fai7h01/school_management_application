package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u ORDER BY u.firstName DESC ")
    List<User> retrieveAllUsersOrderByDesc();

    User findByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);

}
