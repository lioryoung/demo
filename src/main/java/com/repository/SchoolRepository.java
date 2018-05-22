package com.repository;

import com.model.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School,Integer> {
}
