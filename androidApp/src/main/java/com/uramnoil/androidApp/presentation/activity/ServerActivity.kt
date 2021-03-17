package com.uramnoil.androidApp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uramnoil.androidApp.databinding.ActivityServerBinding
import com.uramnoil.androidApp.databinding.ActivityServerListBinding
import com.uramnoil.shared.model.entity.Server

class ServerActivity : AppCompatActivity() {
    companion object {
        val EXTRA_KEY_POSITION = "key_position"
    }

    private val application by lazy {
        getApplication() as ServersApplication
    }

    private val binding by lazy {
        ActivityServerBinding.inflate(layoutInflater)
    }

    lateinit var server: Server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val server = application.servers.getOrNull(intent.getIntExtra(EXTRA_KEY_POSITION, Int.MAX_VALUE))
        requireNotNull(server)
        this.server = server

        binding.apply {
            name.text = server.name
            address.text = server.address.toString()
            port.text = server.port.toString()
            description.text = server.description.toString()
        }
    }
}