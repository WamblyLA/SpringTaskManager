package com.egor.task_manager.TaskController

import com.egor.task_manager.entities.Task
import com.egor.task_manager.entities.User
import com.egor.task_manager.entities.UserRegister
import com.egor.task_manager.service.TaskService
import com.egor.task_manager.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
class UserController(private val userService: UserService) {
    private val log: Logger = LoggerFactory.getLogger(TaskService::class.java)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody reg: UserRegister): User {
        val user = User(
            username = reg.username,
            email = reg.email,
            password = reg.password,
            role = reg.role
        )
        log.info("User registered: ${user.id}")
        return userService.register(user)
    }

    @PutMapping("/{userId}/assign/{taskId}")
    fun assignTask(@PathVariable userId: Long, @PathVariable taskId: Long): ResponseEntity<Task> {
        log.info("Assigning task: $taskId")
        val task = userService.assignTask(userId, taskId)
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{userId}/dessign/{taskId}")
    fun dessignTask(@PathVariable userId: Long, @PathVariable taskId: Long): ResponseEntity<Task> {
        log.info("Designing task: $taskId")
        val task = userService.dessignTask(userId, taskId)
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build()
        }
    }

}