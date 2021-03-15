package com.uramnoil.shared.usecase

import com.uramnoil.shared.model.entity.Server

interface GetServersOutputPort {
	fun setServers(servers: List<Server>)
	fun handleError(exception: Throwable)
}