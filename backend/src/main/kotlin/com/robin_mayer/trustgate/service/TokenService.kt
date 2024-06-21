package com.robin_mayer.trustgate.service

import org.springframework.stereotype.Service

@Service
class TokenService {

	fun generateRefreshToken(): String {
		return generateRandomString(128)
	}

	fun generateVerificationCode(): String {
		return generateRandomString(32)
	}

	fun generateAccessToken(userId: String, email: String, name: String): String {
		return "jwt"
	}

	private fun generateRandomString(length: Int): String {
		val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
		return (1..length)
			.map { allowedChars.random() }
			.joinToString("")
	}
}