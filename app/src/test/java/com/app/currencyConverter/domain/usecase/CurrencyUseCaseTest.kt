package com.app.currencyConverter.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.currencyConverter.datasource.remote.NetworkState
import com.app.currencyConverter.domain.entities.CurrencyDataGenerator.getSuccessCurrencyData
import com.app.currencyConverter.domain.entities.CurrencyRateData
import com.app.currencyConverter.domain.entities.RateList
import com.app.currencyConverter.domain.repository.CurrencyRepository
import com.app.currencyConverter.util.ConstantTest.Companion.EUR_VALUE_TEST
import com.app.currencyConverter.util.ConstantTest.Companion.EXPECTED_ERROR_RESPONSE_CODE
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
class CurrencyUseCaseTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val currencyRepository: CurrencyRepository = mockk()
    private var currencyUseCase : CurrencyUseCase = CurrencyUseCase(currencyRepository)

    @Test
    fun `EUR as a base currency then API responds periodic currency exchange rates update`() {

        val currencyRateData = CurrencyRateData(EUR_VALUE_TEST, emptyMap())

        every { currencyRepository.getRates(anyString())} returns Single.just(currencyRateData)

        val testObserver = currencyRepository.getRates(anyString()).test()

        //when
        verify {  currencyRepository.getRates(anyString()) }

        testObserver
            .awaitCount(2)
            .assertNoErrors()
            .assertValueAt(0, currencyRateData)
    }

    @Test
    fun verifyDataWhenResultIsSuccess() {
        currencyUseCase.getNetworkState().value = NetworkState.Success(getSuccessCurrencyData())
        assertEquals(currencyUseCase.getNetworkState().value?.data,getSuccessCurrencyData())
    }

    @Test
    fun verifyDataWhenResultIsError() {
        val expectedResult = "404"
        currencyUseCase.getNetworkState().value = NetworkState.Error(expectedResult)
        assertEquals(currencyUseCase.getNetworkState().value?.message, EXPECTED_ERROR_RESPONSE_CODE)
    }
}