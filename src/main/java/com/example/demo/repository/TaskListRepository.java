package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
      List<TaskList> findByTitleContaining(String title);
  }