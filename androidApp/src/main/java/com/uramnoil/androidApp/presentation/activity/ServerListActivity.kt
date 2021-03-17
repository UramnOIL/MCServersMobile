package com.uramnoil.androidApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uramnoil.androidApp.databinding.ActivityServerListBinding
import com.uramnoil.androidApp.presentation.ServerListAdapter
import com.uramnoil.shared.model.entity.Server
import com.uramnoil.shared.usecase.getservers.GetServersInputPort
import com.uramnoil.shared.usecase.getservers.GetServersOutputPort
import com.uramnoil.shared.usecase.getservers.GetServersUseCase
import kotlinx.coroutines.Dispatchers
import org.kodein.di.*

class ServerListActivity : AppCompatActivity(), GetServersOutputPort, DIAware {
    override val di by lazy {
        DI.invoke {
            bind<GetServersInputPort>() with factory { output: GetServersOutputPort ->
                GetServersUseCase(
                    output,
                    Dispatchers.Main
                )
            }
        }
    }

    private val input: GetServersInputPort by di.instance(arg = this)

    private val application by lazy { getApplication() as ServersApplication }

    private val binding by lazy {
        ActivityServerListBinding.inflate(layoutInflater)
    }

    private val listView by lazy {
        binding.listView.apply {
            setOnItemClickListener { _, _, position, _ ->
                startActivity(Intent(context, ServerActivity::class.java).apply {
                    putExtra(ServerActivity.EXTRA_KEY_POSITION, position)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        input.getServers()
    }

    override fun setServers(servers: Set<Server>) {
        application.servers = servers.toList()
        listView.adapter = ServerListAdapter(this, android.R.layout.simple_list_item_1, application.servers!!)
    }

    override fun handleError(throwable: Throwable) {
        android.util.Log.w("MainActivity", throwable.toString())
        android.util.Log.w("MainActivity", "Fuga")
    }
}
