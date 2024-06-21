package com.robin_mayer.trustgate.repository

import com.robin_mayer.trustgate.model.db.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long>{
	fun findUserByEmail(email: String): User?
	fun existsUserByEmail(email: String): Boolean
	fun findUserByVerificationCode(verificationCode: String): User?
}