package com.example.mobiledevelopmentcourselabapp.core.presentation

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

open class BasePresenter<V : BaseMvpView> : MvpPresenter<V>() {
    private val compositeDisposable = CompositeDisposable()

    fun Disposable.disposeOnDestroy() {
        compositeDisposable.add(this)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}