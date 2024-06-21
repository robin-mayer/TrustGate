package com.robin_mayer.trustgate.service

import com.robin_mayer.trustgate.model.db.User
import com.robin_mayer.trustgate.model.dto.request.LoginDTO
import com.robin_mayer.trustgate.model.dto.request.SignUpDTO
import com.robin_mayer.trustgate.model.dto.response.AuthDataDTO
import com.robin_mayer.trustgate.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class AuthenticationService (
	private val tokenService: TokenService,
	private val userRepository: UserRepository
) {
	fun signUp(input: SignUpDTO) {
		if(userRepository.existsUserByEmail(input.email)) {
			throw Exception("User already exists")
		}

		if(input.password.length < 8) {
			throw Exception("Password too short")
		}

		// todo: implement

		val newUser = User(
			email = input.email,
			name = input.name,
			password = input.password,
			verificationCode = tokenService.generateVerificationCode()
		)
		userRepository.save(newUser)
	}

	fun signIn(input: LoginDTO): AuthDataDTO {
		val user = userRepository.findUserByEmail(input.email) ?: throw Exception("User not found")
		// todo: implement

		val accessToken = tokenService.generateAccessToken(
			userId = user.userId,
			email = user.email,
			name = user.name
		)
		val refreshToken = tokenService.generateRefreshToken()

		val calendarAccessToken = Calendar.getInstance()
		calendarAccessToken.time = Date()
		calendarAccessToken.add(Calendar.MINUTE, 30)

		val calendarRefreshToken = Calendar.getInstance()
		calendarRefreshToken.time = Date()
		calendarRefreshToken.add(Calendar.HOUR, 24 * 60)

		return AuthDataDTO(
			accessToken = accessToken,
			accessTokenExpiresAt = calendarAccessToken.time,
			refreshToken = refreshToken,
			refreshTokenExpiresAt = calendarRefreshToken.time
		)
	}

	fun verifyUser(verificationCode: String) {
		val user = userRepository.findUserByVerificationCode(verificationCode) ?: throw Exception("User not found")
		user.verified = true
		userRepository.save(user)
	}
}