package com.robin_mayer.trustgate.controller

import com.robin_mayer.trustgate.service.AuthenticationService
import com.robin_mayer.trustgate.model.dto.request.LoginDTO
import com.robin_mayer.trustgate.model.dto.request.SignUpDTO
import com.robin_mayer.trustgate.model.dto.response.AuthDataDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
	private val authenticationService: AuthenticationService
) {

	@PostMapping("/login")
	fun login(
		@RequestBody loginDTO: LoginDTO
	): ResponseEntity<AuthDataDTO> {
		val authData = authenticationService.signIn(loginDTO)
		return ResponseEntity.ok(authData)
	}

	@PostMapping("/signup")
	fun signUp(
		@RequestBody signUpDTO: SignUpDTO
	): ResponseEntity<Boolean> {
		authenticationService.signUp(signUpDTO)
		return ResponseEntity(HttpStatus.CREATED)
	}

	@PatchMapping("/verify/{verificationCode}")
	fun verify(
		@PathVariable verificationCode: String
	): ResponseEntity<Boolean> {
		authenticationService.verifyUser(verificationCode)
		return ResponseEntity(HttpStatus.OK)
	}
}