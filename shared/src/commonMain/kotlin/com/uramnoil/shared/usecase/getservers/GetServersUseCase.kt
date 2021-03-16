package com.uramnoil.shared.usecase.getservers

import com.uramnoil.shared.ServerListService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class GetServersUseCase(private val output: GetServersOutputPort, override val coroutineContext: CoroutineContext) : CoroutineScope, GetServersInputPort {

	override fun getServers() {
		val handler = CoroutineExceptionHandler { _, throwable ->
			output.handleError(throwable)
		}

		launch(handler) {
			val result = withContext(Dispatchers.Default) {
				ServerListService.fetchServerList()
			}
			output.setServers(result)
		}
	}
}