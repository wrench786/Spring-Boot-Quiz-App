package com.devminhaj.repository;

import com.devminhaj.model.Question;
import com.devminhaj.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result,Integer> {
}
