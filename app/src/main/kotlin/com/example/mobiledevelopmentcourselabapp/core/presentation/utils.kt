package com.example.mobiledevelopmentcourselabapp.core.presentation

import io.reactivex.rxjava3.core.Single

fun <T : Any> Single<T>.withLoadingView(view: BaseMvpView) =
    this.doOnSubscribe { view.setLoadingVisibility(true) }
        .doFinally { view.setLoadingVisibility(false) }