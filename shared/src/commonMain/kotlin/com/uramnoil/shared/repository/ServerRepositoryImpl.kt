package com.uramnoil.shared.repository

import com.uramnoil.shared.Server
import com.uramnoil.shared.ServerListLoader

class ServerRepositoryImpl : ServerRepository {
	override suspend fun getServers(): List<Server> {
		return ServerListLoader.loadServerList()
	}
}