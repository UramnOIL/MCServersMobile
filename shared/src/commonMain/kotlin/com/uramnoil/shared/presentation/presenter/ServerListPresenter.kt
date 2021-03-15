package com.uramnoil.shared.presentation.presenter

import org.koin.core.component.KoinComponent

interface ServerListPresenter {
	val view: ServerListView
}

interface ServerListView : KoinComponent {
	var presenter: ServerListPresenter?
}