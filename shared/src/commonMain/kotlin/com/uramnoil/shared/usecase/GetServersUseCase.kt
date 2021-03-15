package com.uramnoil.shared.usecase

import com.uramnoil.shared.repository.ServerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetServersUseCase(val output: GetServersOutputPort, val repository: ServerRepository) : CoroutineScope, GetServersInputPort {
	override val coroutineContext = Dispatchers.Main

	override fun getServers() {
		launch {
			output.setServers(repository.getServers())
		}
	}
}