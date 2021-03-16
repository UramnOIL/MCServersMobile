package com.uramnoil.shared.usecase.getservers

import com.uramnoil.shared.ServerListService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetServersUseCase(private val output: GetServersOutputPort) : CoroutineScope, GetServersInputPort {
	override val coroutineContext = Dispatchers.Default

	override fun getServers() {
		launch {
			runCatching { ServerListService.fetchServerList() }
				.onSuccess { output.setServers(it) }
				.onFailure { output.handleError(it) }
		}
	}
}