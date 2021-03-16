package com.uramnoil.shared.presentation.presenter

import com.uramnoil.shared.usecase.getservers.GetServersOutputPort


interface ServerListPresenter : GetServersOutputPort {
	val view: ServerListView
}

interface ServerListView {
	val presenter: ServerListPresenter
}