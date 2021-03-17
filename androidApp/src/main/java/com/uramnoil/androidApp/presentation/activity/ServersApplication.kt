package com.uramnoil.androidApp.presentation.activity

import android.app.Application
import com.uramnoil.shared.model.entity.Server

class ServersApplication : Application() {
    var servers = listOf<Server>()
}