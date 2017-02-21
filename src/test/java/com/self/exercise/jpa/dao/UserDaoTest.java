package com.self.exercise.jpa.dao;

import com.google.common.collect.Iterables;
import com.self.exercise.jpa.model.Entry;
import com.self.exercise.jpa.model.Role;
import com.self.exercise.jpa.model.User;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static  org.hamcrest.Matchers.*;


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
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private EntryDao entryDao;

    @Before
    public void setUserDaoTest() {
        userRole = new Role("user");
        adminRole = new Role("admin");
        user1 = new User("user1", "user1", userRole, adminRole);
        user2 = new User("user2", "user2", userRole);
        userDao.save(user1);
        userDao.save(user2);
        entry1 = new Entry("Title 1", "Body 1", user1);
        entry2 = new Entry("Title 2", "Body 2", user2);
        entry3 = new Entry("Title 3", "Body 3", user2);
        entryDao.save(entry1);
        entryDao.save(entry2);
        entryDao.save(entry3);
    }

    @Test
    @Transactional
    public void getAllUsersTest() {
        Iterable<User> users = userDao.findAll();
        for (User u : users) {
            log.info("{}", u);
        }
        int size = Iterables.size(users);
        assertEquals("Size should be 2", 2, size);
    }

    @Test
    @Transactional
    public void getUsersByRoles() {
        List<User> users = userDao.findAllByRoles(adminRole);
        for (User u : users) {
            log.info("Admin: {}", u);
        }
        int size = Iterables.size(users);
        assertEquals("There should only be one admin", 1, size);
        users = userDao.findAllByRoles(userRole);
        for (User u : users) {
            log.info("User: {}", u);
        }
        size = Iterables.size(users);
        assertEquals("There should be two users", 2, size);
    }

    @Test
    @Transactional
    public void getUserByEmailOrNameTest() {
        User user = userDao.findOneByUserNameOrEmail("user1", "bla");
        log.info("User found: {}", user);
        user = userDao.findOneByUserNameOrEmail("bla", "user2");
        log.info("User found: {}", user);
    }

    @Test
    @Transactional
    public void saveEntries() {
        Entry entry = entryDao.findOne(entry1.getId());
        log.info("{}", entry);
        assertThat(entry.getUser(), notNullValue());
    }

    @Test
    @Transactional
    public void countEntriesUserHas() {
        long countUser1 = entryDao.countByUser(user1);
        long countUser2 = entryDao.countByUserId(user2.getId());
        assertThat(countUser1, is(1L));
        assertThat(countUser2, is(2L));
    }

}