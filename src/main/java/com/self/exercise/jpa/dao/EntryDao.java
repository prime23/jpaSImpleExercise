package com.self.exercise.jpa.dao;

import com.self.exercise.jpa.model.Entry;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by prime23 on 2/16/17.
 */
public interface EntryDao extends CrudRepository<Entry, Long> {
}
