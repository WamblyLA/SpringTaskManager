package com.egor.task_manager.service

import com.egor.task_manager.entities.Task
import com.egor.task_manager.entities.TaskStatus
import com.egor.task_manager.repository.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAll(status: TaskStatus?): List<Task> {
        return if (status != null) {
            taskRepository.findAllByStatus(status)
        } else {
            taskRepository.findAll()
        }
    }
    fun getById(id: Long): Task? {
        return taskRepository.findByIdOrNull(id)
    }

    fun create(task: Task): Task {
        return taskRepository.save(task)
    }

    fun update(id: Long, updating: Task): Task? {
        val task = taskRepository.findByIdOrNull(id) ?: return null
        task.title = updating.title
        task.description = updating.description
        task.status = updating.status
        return taskRepository.save(task)
    }

    fun delete(id: Long) {
        taskRepository.deleteById(id)
    }
    fun deleteAll() {
        taskRepository.deleteAll()
    }
}