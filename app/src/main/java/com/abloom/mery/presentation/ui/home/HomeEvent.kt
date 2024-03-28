package com.abloom.mery.presentation.ui.home

sealed interface HomeEvent {
    data object LoginFail : HomeEvent
}
