package com.egor.task_manager.TaskController

import com.egor.task_manager.entities.Task
import com.egor.task_manager.entities.TaskRegister
import com.egor.task_manager.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.egor.task_manager.entities.TaskStatus
import org.slf4j.LoggerFactory
import org.slf4j.Logger
@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    private val log: Logger = LoggerFactory.getLogger(TaskService::class.java)
    @GetMapping
    fun findAll(@RequestParam(required=false) status: TaskStatus?): ResponseEntity<List<Task>> {
        val tasks = taskService.getAll(status)
        if (tasks.isEmpty()) {
            log.info("No tasks found")
            return ResponseEntity.notFound().build();
        } else {
            log.error("Found ${tasks.size} tasks")
            return ResponseEntity.ok(tasks)
        }
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getById(id);
        return if (task != null) {
            log.info("Found task with id: $id")
            ResponseEntity.ok(task)
        } else {
            log.error("No such task with id: $id")
            ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody taskRegister: TaskRegister): Task {
        val task = Task(
            title = taskRegister.title,
            description = taskRegister.description,
            status = taskRegister.status,
        )
        taskRegister.parentId?.let {
            val parentTask = taskService.getById(it)
            if (parentTask != null) {
                task.parent = parentTask
            } else {
                log.warn("No such parent task with id: $it")
            }
        }
        log.info("Created new task")
        val created = taskService.create(task)
        return taskService.create(created)
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val updated = taskService.update(id, task)
        return if (updated != null) {
            log.info("Updated task with id: $id")
            ResponseEntity.ok(updated)
        } else {
            log.error("Couldn't update task with id: $id")
            ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val deleted = taskService.getById(id);
        return if (deleted != null) {
            log.info("Deleted task with id: $id")
            taskService.delete(id)
            ResponseEntity.noContent().build();
        } else {
            log.error("Couldn't delete task with id: $id")
            ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping
    fun deleteAll() {
        log.info("Deleted all tasks")
        return taskService.deleteAll()
    }
}