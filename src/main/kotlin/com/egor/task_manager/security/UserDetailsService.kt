package com.egor.task_manager.security

import com.egor.task_manager.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class UserDetailsService (
    private val userRepository: UserRepository,
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User $username not found")
        val permissions = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
        return org.springframework.security.core.userdetails.User(
            user.username, user.password, permissions
        )
    }
}