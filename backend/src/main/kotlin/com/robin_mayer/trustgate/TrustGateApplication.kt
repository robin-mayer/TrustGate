package com.robin_mayer.trustgate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrustGateApplication

fun main(args: Array<String>) {
	runApplication<TrustGateApplication>(*args)
}
