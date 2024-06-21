package com.robin_mayer.trustgate.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenService {

	@Value("\${authentication.refresh_token.length}")
	private lateinit var refreshTokenLength: String

	@Value("\${authentication.verification_code.length}")
	private lateinit var verificationCodeLength: String

	fun generateRefreshToken(): String {
		return generateRandomString(refreshTokenLength.toInt())
	}

	fun generateVerificationCode(): String {
		return generateRandomString(verificationCodeLength.toInt())
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