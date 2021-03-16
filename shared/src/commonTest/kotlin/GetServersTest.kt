import com.uramnoil.shared.ServerListService
import com.uramnoil.shared.model.entity.Server

import com.uramnoil.shared.runBlocking
import com.uramnoil.shared.usecase.getservers.GetServersInputPort
import com.uramnoil.shared.usecase.getservers.GetServersOutputPort
import com.uramnoil.shared.usecase.getservers.GetServersUseCase
import kotlinx.coroutines.*
import org.kodein.di.*
import kotlin.test.Test


class GetServersView : GetServersOutputPort {
	override fun setServers(servers: Set<Server>) {
		println(servers.joinToString("\n") { it.name })
	}

	override fun handleError(exception: Throwable) {
		println(exception.message)
	}
}

class GetServersTest : DIAware {

	override val di: DI = DI.invoke {
		bind<GetServersOutputPort>() with provider { GetServersView() }
		bind<GetServersInputPort>() with provider { GetServersUseCase(instance()) }
	}

	private val getServersInputPort: GetServersInputPort by di.instance()

	@Test
	fun `APIからデータを取得する`() = runBlocking {
		kotlin.runCatching {
			ServerListService.fetchServerList()
		}.onFailure {
			it.printStackTrace()
		}.onSuccess {
			println(it.joinToString("\n") { server -> server.name })
		}
	}

	@Test
	fun `Kodeinが正常に機能してるか確認する`() = runBlocking {
		getServersInputPort.getServers()
		delay(10000)
	}
}