package com.stiehl.bookshelf.adapters

import com.stiehl.bookshelf.model.Book

interface BookAdapterListener {
    fun onBookSelected(book: Book)
}