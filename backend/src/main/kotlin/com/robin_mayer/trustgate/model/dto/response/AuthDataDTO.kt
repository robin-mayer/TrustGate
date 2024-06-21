package com.robin_mayer.trustgate.model.dto.response

import java.util.Date

data class AuthDataDTO (
	val accessToken: String,
	val accessTokenExpiresAt: Date,
	val refreshToken: String,
	val refreshTokenExpiresAt: Date
)