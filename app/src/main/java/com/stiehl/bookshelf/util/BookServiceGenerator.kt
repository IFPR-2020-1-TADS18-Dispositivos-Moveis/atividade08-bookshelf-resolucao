package com.stiehl.bookshelf.util

import com.stiehl.bookshelf.api.BookService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookServiceGenerator {
    companion object {
        private var retrofit: Retrofit? = null
        private var service: BookService? = null

        fun getService(): BookService {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit!!.create(BookService::class.java)
            }
            return service!!
        }
    }
}