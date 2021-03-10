package com.uramnoil.shared.usecase

import com.uramnoil.shared.repository.ServerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetServersUseCase : CoroutineScope, GetServersInputPort, KoinComponent {
	override val coroutineContext = Dispatchers.Main

	val output: GetServersOutputPort by inject()
	val repository: ServerRepository by inject()

	override fun getServers() {
		launch {
			output.setServers(repository.getServers())
		}
	}
}