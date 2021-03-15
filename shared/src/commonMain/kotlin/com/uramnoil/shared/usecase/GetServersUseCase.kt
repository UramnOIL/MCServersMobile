package com.uramnoil.shared.usecase

import com.uramnoil.shared.ServerListService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetServersUseCase(private val output: GetServersOutputPort, private val repository: ServerListService) : CoroutineScope, GetServersInputPort {
	override val coroutineContext = Dispatchers.Main

	override fun getServers() {
		launch {
			output.setServers(repository.fetchServerList())
		}
	}

}