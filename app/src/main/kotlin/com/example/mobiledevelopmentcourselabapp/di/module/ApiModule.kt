package com.example.mobiledevelopmentcourselabapp.di.module

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.provider.RetrofitProvider
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun provideChuckApi(retrofitProvider: RetrofitProvider): ChuckApi {
        return retrofitProvider.retrofitChuckApi
    }
}
