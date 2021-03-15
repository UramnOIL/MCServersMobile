package com.uramnoil.shared.presentation.presenter


interface ServerListPresenter {
	val view: ServerListView
}

interface ServerListView {
	var presenter: ServerListPresenter?
}