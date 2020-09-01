package com.stiehl.bookshelf.api

import com.stiehl.bookshelf.api.results.GetChaptersResult
import com.stiehl.bookshelf.model.Book
import com.stiehl.bookshelf.model.Chapter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("books")
    fun getAll(): Call<List<Book>>

    @GET("books/{id}")
    fun get(): Call<Book>

    @GET("chapters/{bookId}")
    fun getChapters(@Path("bookId") bookId: Long): Call<GetChaptersResult>
}