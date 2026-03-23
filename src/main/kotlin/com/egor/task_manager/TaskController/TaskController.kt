package com.egor.task_manager.TaskController

import com.egor.task_manager.entities.Task
import com.egor.task_manager.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    @GetMapping
    fun findAll(): ResponseEntity<List<Task>> {
        val tasks = taskService.getAll()
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tasks)
        }
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getById(id);
        return if (task != null) {
            ResponseEntity.ok(task)
        } else {
            ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody task: Task): Task {
        return taskService.create(task)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val updated = taskService.update(id, task)
        return if (updated != null) {
            ResponseEntity.ok(updated)
        } else {
            ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val deleted = taskService.getById(id);
        return if (deleted != null) {
            taskService.delete(id)
            ResponseEntity.noContent().build();
        } else {
            ResponseEntity.notFound().build();
        }
    }
}