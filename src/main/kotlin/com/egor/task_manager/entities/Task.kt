package com.egor.task_manager.entities

import jakarta.persistence.*
import java.time.LocalDateTime

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED
}
@Entity
@Table(name = "tasks")
class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var title: String = ""
    var description: String = ""

    @Enumerated(EnumType.STRING)
    var status: TaskStatus = TaskStatus.TODO

    val createdAt: LocalDateTime = LocalDateTime.now()
}