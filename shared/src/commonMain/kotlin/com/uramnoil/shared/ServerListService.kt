package com.uramnoil.shared

import com.uramnoil.shared.model.entity.Server
import com.uramnoil.shared.model.entity.StatusAndServers
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ServerListService {
	suspend fun fetchServerList(): List<Server> {
		val response = HttpClient().get<HttpResponse> {
			url("https://mcservers.jp/api/v1/server/list")
		}
		if (response.status != HttpStatusCode.OK) {
			throw Exception()
		}
		return Json.decodeFromString<StatusAndServers>(response.readText()).servers
	}
}