package com.robin_mayer.trustgate.model.db

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
class User (
	var email: String,
	var name: String,
	var password: String,
	val verificationCode: String
) {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null
	var userId: String = UUID.randomUUID().toString()
	var lastLogin: Date = Date()
	var verified: Boolean = false
}