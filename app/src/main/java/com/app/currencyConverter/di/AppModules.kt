package com.app.currencyConverter.di

import com.app.currencyConverter.BuildConfig
import com.app.currencyConverter.commons.util.UiHelper
import com.app.currencyConverter.datasource.remote.CurrencyApi
import com.app.currencyConverter.datasource.remote.createNetworkClient
import com.app.currencyConverter.domain.repository.CurrencyRepository
import com.app.currencyConverter.domain.usecase.CurrencyUseCase
import com.app.currencyConverter.ui.currency.CurrencyVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(viewModelModule,
            repositoryModule,
            networkModule,
            useCaseModule,
            uiHelperModule)
    )
}

val viewModelModule = module {
    viewModel { CurrencyVM(currencyUseCase = get()) }
}

val repositoryModule = module {
    single { CurrencyRepository(currencyApi = get()) }
}

val useCaseModule = module {
    single { CurrencyUseCase(currencyRepository = get()) }
}

val networkModule = module {
    single { currencyApi }
}

val uiHelperModule = module {
    single { UiHelper(androidContext()) }
}

private val retrofit : Retrofit = createNetworkClient(BuildConfig.BASE_URL)

private val currencyApi : CurrencyApi = retrofit.create(CurrencyApi::class.java)