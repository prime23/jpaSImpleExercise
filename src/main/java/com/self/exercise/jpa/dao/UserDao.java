package com.self.exercise.jpa.dao;

import com.self.exercise.jpa.model.Role;
import com.self.exercise.jpa.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prime23 on 2/6/17.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long>{
    List<User> findAllByRoles(Role role);
    User findOneByUserNameOrEmail(String name, String email);
}
