package com.egor.task_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskManagerApp

fun main(args: Array<String>) {
    runApplication<TaskManagerApp>(*args)
}