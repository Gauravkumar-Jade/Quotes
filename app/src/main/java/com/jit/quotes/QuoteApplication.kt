package com.jit.quotes

import android.app.Application
import com.jit.quotes.repository.QuotesRepository

class QuoteApplication:Application() {
    lateinit var repository: QuotesRepository
    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {
        repository = QuotesRepository()
    }
}