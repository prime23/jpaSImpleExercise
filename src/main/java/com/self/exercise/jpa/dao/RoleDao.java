package com.self.exercise.jpa.dao;

import com.self.exercise.jpa.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by prime23 on 2/6/17.
 */
public interface RoleDao extends CrudRepository<Role, Long> {
}
