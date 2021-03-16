package com.uramnoil.shared.usecase.getservers

import com.uramnoil.shared.ServerListService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetServersUseCase(private val output: GetServersOutputPort) : CoroutineScope, GetServersInputPort {
	override val coroutineContext = Dispatchers.Default

	override fun getServers() {
		val handler = CoroutineExceptionHandler { _, throwable ->
			output.handleError(throwable)
		}

		launch(handler) {
			output.setServers(ServerListService.fetchServerList())
		}
	}
}