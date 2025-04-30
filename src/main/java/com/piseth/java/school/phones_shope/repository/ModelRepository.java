package com.piseth.java.school.phones_shope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piseth.java.school.phones_shope.entity.Model;
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>{

}
