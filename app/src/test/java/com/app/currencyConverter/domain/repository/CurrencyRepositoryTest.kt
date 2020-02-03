package com.app.currencyConverter.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.currencyConverter.domain.entities.CurrencyRateData
import com.app.currencyConverter.util.ConstantTest.Companion.EUR_VALUE_TEST
import com.app.currencyConverter.util.ConstantTest.Companion.INR_VALUE_TEST
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single.just
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrencyRepositoryTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val currencyRepository: CurrencyRepository = mockk()

    @Test
    fun `request base currency EUR and answers should return currency exchange data`() {

        every { currencyRepository.getRates(EUR_VALUE_TEST) }returns just(
                    CurrencyRateData(
                        EUR_VALUE_TEST,
                        mapOf(Pair("AUD", 1.6114),
                            Pair("BGN", 1.9497))
                    )
                )

        //when
        val testSubscriber = currencyRepository.getRates(EUR_VALUE_TEST).test()

        //then
        verify { currencyRepository.getRates(EUR_VALUE_TEST) }
        testSubscriber
            .awaitCount(1)
            .assertNoErrors()
            .assertValue(CurrencyRateData(EUR_VALUE_TEST, mapOf(Pair("AUD", 1.6114),Pair("BGN", 1.9497))))
    }

    @Test
    fun `select base currency different than start base currency answers should return different exchange data`() {


        every { currencyRepository.getRates(INR_VALUE_TEST) } returns
                just(
                    CurrencyRateData(INR_VALUE_TEST,
                        mapOf(Pair("EUR", 83.41),
                                Pair("BRL", 4.7769))
                    )
                )

        //when
        val testSubscriber = currencyRepository.getRates(INR_VALUE_TEST).test()

        //then
        verify { currencyRepository.getRates(INR_VALUE_TEST) }
        testSubscriber
            .awaitCount(1)
            .assertNoErrors()
            .assertValue(CurrencyRateData(INR_VALUE_TEST, mapOf(Pair("EUR", 83.41), Pair("BRL", 4.7769))))
    }
}