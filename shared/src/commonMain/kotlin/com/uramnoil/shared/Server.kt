package com.uramnoil.shared

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject

object ObjectToMapSerializer : KSerializer<Map<String, String>> {
	override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

	override fun deserialize(decoder: Decoder): Map<String, String> {
		require(decoder is JsonDecoder)
		val element = decoder.decodeJsonElement()
		return if (element is JsonObject) {
			element.mapValues { it.value.toString() }
		} else {
			mapOf()
		}
	}

	override fun serialize(encoder: Encoder, value: Map<String, String>) {
		TODO("Not yet implemented")
	}
}

object BoolSerializer : KSerializer<Boolean> {
	override val descriptor = Boolean.serializer().descriptor

	override fun deserialize(decoder: Decoder): Boolean {
		require(decoder is JsonDecoder)
		return decoder.decodeInt() == 1
	}

	override fun serialize(encoder: Encoder, value: Boolean) {
		TODO("Not yet implemented")
	}
}


@Serializable
data class User(
	val id: Int,
	val name: String,
	val description: String?,
	@SerialName("icon_image_id")
	val iconImageId: String?,
	@SerialName("icon_image")
	val iconImage: Image?,
	@SerialName("created_at")
	val createdAt: String,
	@SerialName("updated_at")
	val updatedAt: String,
)


@Serializable
data class Image(
	val id: String,
	val format: String,
	val type: Int,
	val url: String,
)


@Serializable
data class LatestPing(
	@SerialName("server_id")
	val serverId: Int,
	@SerialName("is_running")
	@Serializable(with = BoolSerializer::class)
	val isRunning: Boolean,
	val millisecond: Int?,
	val protocol: Int?,
	val version: String?,
	@SerialName("current_player")
	val currentPlayer: Int?,
	@SerialName("max_player")
	val maxPlayer: Int?,
	@SerialName("created_at")
	val createdAt: String,
)


@Serializable
data class YesterdayStatistics(
	val date: String,
	val type: Int,
	@SerialName("server_id")
	val serverId: Int,
	@SerialName("all_ping_count")
	val allPingCount: Int,
	@SerialName("valid_ping_count")
	val validPingCount: Int,
	@SerialName("average_player")
	val averagePlayer: Float,
	@SerialName("max_player")
	val maxPlayer: Int,
	@SerialName("created_at")
	val createdAt: String,
)


@Serializable
data class Votes(
	val entire: Int,
	val recently: Int,
)


@Serializable
data class Server(
	val id: Int,
	@SerialName("user_id")
	val userId: Int,
	val name: String,
	val address: String? = null,
	val port: Int? = null,
	val description: String?,
	val color: String?,
	val categories: List<Int>,
	@SerialName("web_sites")
	@Serializable(with = ObjectToMapSerializer::class)
	val websites: Map<String, String>,
	@SerialName("top_image_id")
	val topImageId: String?,
	@SerialName("back_image_id")
	val backImageId: String?,
	@SerialName("is_verified")
	@Serializable(with = BoolSerializer::class)
	val isVerified: Boolean,
	@SerialName("is_archived")
	@Serializable(with = BoolSerializer::class)
	val isArchived: Boolean,
	@SerialName("is_display_server")
	@Serializable(with = BoolSerializer::class)
	val isDisplayServer: Boolean,
	@SerialName("is_display_address")
	@Serializable(with = BoolSerializer::class)
	val isDisplayAddress: Boolean,
	@SerialName("is_display_statistics")
	@Serializable(with = BoolSerializer::class)
	val isDisplayStatistics: Boolean,
	@SerialName("created_at")
	val createdAt: String,
	@SerialName("updated_at")
	val updatedAt: String,
	val user: User,
	@SerialName("top_image")
	val topImage: Image?,
	@SerialName("back_image")
	val backImage: Image?,
	@SerialName("latest_ping")
	val latestPing: LatestPing,
	@SerialName("yesterday_statistics")
	val yesterdayStatistics: YesterdayStatistics,
	val votes: Votes,
)


@Serializable
data class StatusAndServers(
	val status: Int,
	val servers: List<Server>,
)