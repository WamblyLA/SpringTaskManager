package com.egor.task_manager.repository

import com.egor.task_manager.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface TaskRepository : JpaRepository<Task, Long>