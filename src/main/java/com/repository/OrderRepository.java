package com.repository;

import com.model.Indent;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Indent,Integer> {
}
