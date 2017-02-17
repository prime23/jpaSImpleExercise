package com.self.exercise.jpa.dao;

import com.self.exercise.jpa.model.Role;
import com.self.exercise.jpa.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by prime23 on 2/16/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    private User user1;
    private User user2;
    private Role userRole;
    private Role adminRole;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Before
    public void setUserDaoTest() {
        userRole = new Role("user");
        adminRole = new Role("admin");
        user1 = new User("user1", "user1", userRole, adminRole);
        user2 = new User("user2", "user2", userRole);
        userDao.save(user1);
        userDao.save(user2);
    }

    @Test
    @Transactional
    public void getAllUsersTest() {
        Iterable<User> users = userDao.findAll();
        for(User u : users) {
            log.info("{}", u);
        }
    }

    @Test
    @Transactional
    public void getUsersByRoles() {
        List<User> users = userDao.findAllByRoles(adminRole);
        for(User u : users) {
            log.info("Admin: {}", u);
        }
        users = userDao.findAllByRoles(userRole);
        for(User u : users) {
            log.info("User: {}", u);
        }
    }

    @Test
    @Transactional
    public void getUserByEmailOrNameTest() {
        User user = userDao.findOneByUserNameOrEmail("user1", "bla");
        log.info("User found: {}", user);
        user = userDao.findOneByUserNameOrEmail("bla", "user2");
        log.info("User found: {}", user);
    }
}