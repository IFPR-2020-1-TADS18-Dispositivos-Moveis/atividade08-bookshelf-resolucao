package com.stiehl.bookshelf.fragments

import com.stiehl.bookshelf.model.Book

interface BookListFragmentListener {
    fun onBookSelected(book: Book)
}