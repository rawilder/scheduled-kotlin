package dev.awilder

import io.micronaut.runtime.Micronaut.*
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import reactor.core.publisher.Mono

fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("dev.awilder")
		.start()
}

@Singleton
class Scheduler {
	// regular use case
	@Scheduled(fixedDelay = "1s")
	fun test() {
		println("Hello World")
	}

	// doesn't work
	@Scheduled(fixedDelay = "1s")
	suspend fun suspendTest() {
		println("Hello World")
	}

	// workaround?
	@Scheduled(fixedDelay = "1s")
	fun jobLaunchedSuspendTest() {
		CoroutineScope(Dispatchers.Default).launch {
			println("job launched suspending Hello World")
		}
	}
}

