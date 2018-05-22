package com.repository;

import com.model.IndentDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IndentDetailRepository extends CrudRepository<IndentDetail,Integer> {
    List<IndentDetail> findByCommentState(boolean state);
}
