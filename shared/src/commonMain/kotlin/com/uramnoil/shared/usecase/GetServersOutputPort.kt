package com.uramnoil.shared.usecase

import com.uramnoil.shared.Server

interface GetServersOutputPort {
	fun setServers(servers: List<Server>)
}