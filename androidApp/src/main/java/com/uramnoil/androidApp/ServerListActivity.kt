package com.uramnoil.androidApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uramnoil.androidApp.databinding.ActivityMainBinding
import com.uramnoil.shared.model.entity.Server
import com.uramnoil.shared.usecase.getservers.GetServersInputPort
import com.uramnoil.shared.usecase.getservers.GetServersOutputPort
import com.uramnoil.shared.usecase.getservers.GetServersUseCase
import kotlinx.coroutines.Dispatchers
import org.kodein.di.*

class ServerListActivity : AppCompatActivity(), GetServersOutputPort, DIAware {
    override val di by lazy {
        DI.invoke {
            bind<GetServersInputPort>() with factory { output: GetServersOutputPort -> GetServersUseCase(output, Dispatchers.Main) }
        }
    }

    private val input: GetServersInputPort by di.instance(arg = this)

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val textView by lazy {
        binding.textView
    }

    private val buttonView by lazy {
        binding.buttonView.apply {
            setOnClickListener {
                input.getServers()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        textView.text = "うんちぶり"

        input.getServers()
    }

    override fun setServers(servers: Set<Server>) {
        textView.text = servers.joinToString(", ") { it.name }
        android.util.Log.i("MainActivity", "Hoge")
    }

    override fun handleError(throwable: Throwable) {
        android.util.Log.w("MainActivity", throwable.toString())
        android.util.Log.w("MainActivity", "Fuga")
    }
}
