package com.egor.task_manager.repository

import com.egor.task_manager.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.egor.task_manager.entities.TaskStatus
@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findAllByStatus(status: TaskStatus): List<Task>
}