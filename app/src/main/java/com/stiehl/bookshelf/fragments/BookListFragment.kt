package com.stiehl.bookshelf.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stiehl.bookshelf.R
import com.stiehl.bookshelf.adapters.BookAdapter
import com.stiehl.bookshelf.adapters.BookAdapterListener
import com.stiehl.bookshelf.model.Book
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_book_list.view.*
import java.lang.Exception

class BookListFragment(private val listener: BookListFragmentListener? = null) : Fragment(),
    BookAdapterListener {
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        adapter = BookAdapter(this)
        view.listBooks.adapter = adapter
        view.listBooks.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        return view
    }

    override fun onBookSelected(book: Book) {
        val bundle = Bundle()
        bundle.putSerializable("book", book)
        try {
            findNavController().navigate(R.id.navigateToBookFragment, bundle)
        } catch (e: Exception) {
        }

        listener?.onBookSelected(book)
    }

}