package com.egor.task_manager.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
class Task(
    var title: String = "",
    var description: String = "",

    @Enumerated(EnumType.STRING)
    var status: TaskStatus = TaskStatus.TODO,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var belongsTo: User? = null,

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: Task? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    val createdAt: LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "parent", cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    var subtasks: List<Task> = listOf()
}
data class TaskRegister (
    var title: String,
    var description: String,
    var status: TaskStatus = TaskStatus.TODO,
    var parentId: Long? = null,
)