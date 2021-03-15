import com.uramnoil.shared.ServerListLoader
import com.uramnoil.shared.runBlocking
import kotlin.test.Test

class GetServersTest {
	val loader = ServerListLoader

	@Test
	fun `お試し`() = runBlocking {
		kotlin.runCatching {
			loader.loadServerList()
		}.onFailure {
			it.printStackTrace()
		}.onSuccess {
			println(it.joinToString("\n") { server -> server.name })
		}
	}
}