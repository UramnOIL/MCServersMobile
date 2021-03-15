package com.uramnoil.shared.repository

import com.uramnoil.shared.Server
import com.uramnoil.shared.ServerListLoader

interface ServerRepository {
	suspend fun getServers(): List<Server>
}