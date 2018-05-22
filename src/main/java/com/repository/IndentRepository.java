package com.repository;

import com.model.Indent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IndentRepository extends CrudRepository<Indent,Integer> {
    List<Indent> findByState(int state);
}
