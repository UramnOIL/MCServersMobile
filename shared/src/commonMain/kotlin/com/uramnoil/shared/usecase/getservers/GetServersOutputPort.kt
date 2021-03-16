package com.uramnoil.shared.usecase.getservers

import com.uramnoil.shared.model.entity.Server

interface GetServersOutputPort {
	fun setServers(servers: Set<Server>)
	fun handleError(exception: Throwable)
}