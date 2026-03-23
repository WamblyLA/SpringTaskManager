package com.egor.task_manager.service

import com.egor.task_manager.entities.Task
import com.egor.task_manager.entities.User
import com.egor.task_manager.repository.TaskRepository
import com.egor.task_manager.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity

@Service
class UserService(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(user: User): User{
        user.id = null
        user.password = passwordEncoder.encode(user.password).toString()
        return userRepository.save(user)
    }
    fun assignTask(userId: Long, taskId: Long): Task? {
        val user = userRepository.findByIdOrNull(userId) ?: return null
        val task = taskRepository.findByIdOrNull(taskId) ?: return null
        task.belongsTo = user
        return taskRepository.save(task)
    }
    fun dessignTask(userId: Long, taskId: Long): Task? {
        val task = taskRepository.findByIdOrNull(taskId) ?: return null
        if (task.belongsTo?.id == userId) {
            task.belongsTo = null
            return taskRepository.save(task)
        }
        return null
    }
}