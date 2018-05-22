package com.repository;

import com.model.FoodIndent;
import com.model.Indent;
import com.model.IndentDetail;
import com.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodIndentRepository extends CrudRepository<FoodIndent,Integer> {
    List<FoodIndent> findByUser(User user);
    List<FoodIndent> findByState(boolean state);
}
