package com.egor.task_manager.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "db_user")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    @Email
    var email: String,
    var password: String,
    var role: UserRole = UserRole.USER
)
data class UserRegister(
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole = UserRole.USER
)