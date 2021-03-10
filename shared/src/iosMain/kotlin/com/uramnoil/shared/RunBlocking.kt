package com.uramnoil.shared

actual fun runBlocking(block: suspend () -> Unit) = kotlinx.coroutines.runBlocking { block() }