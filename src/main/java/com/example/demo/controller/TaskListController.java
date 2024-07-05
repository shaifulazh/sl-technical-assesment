package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TaskList;
import com.example.demo.repository.TaskListRepository;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class TaskListController {

    @Autowired
    TaskListRepository taskListRepository;

    @GetMapping("/tasklist")
    public ResponseEntity<List<TaskList>> getAllTasklist(@RequestParam(required = false) String title) {
        try {
            List<TaskList> tasklist = new ArrayList<TaskList>();

            if (title == null) {
                taskListRepository.findAll().forEach(tasklist::add);
            } else {
                taskListRepository.findByTitleContaining(title).forEach(tasklist::add);
            }

            if (tasklist.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasklist, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasklist/{id}")
    public ResponseEntity<TaskList> getTutorialById(@PathVariable("id") long id) {
        Optional<TaskList> taskListData = taskListRepository.findById(id);

        if (taskListData.isPresent()) {
            return new ResponseEntity<>(taskListData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tasklist")
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList tasklist) {
        try {
            TaskList _taskList = taskListRepository.save(new TaskList(tasklist.getTitle(), tasklist.getDescription(), false));
            return new ResponseEntity<>(_taskList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tasklist/{id}")
    public ResponseEntity<TaskList> updateTutorial(@PathVariable("id") long id, @RequestBody TaskList tasklist) {
        Optional<TaskList> taskListData = taskListRepository.findById(id);

        if (taskListData.isPresent()) {
            TaskList _tutorial = taskListData.get();
            _tutorial.setCompleted(tasklist.isCompleted());
            return new ResponseEntity<>(taskListRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasklist/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        Optional<TaskList> taskListData = taskListRepository.findById(id);
        if (!taskListData.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            taskListRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
