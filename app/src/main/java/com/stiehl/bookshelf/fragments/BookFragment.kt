package com.stiehl.bookshelf.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stiehl.bookshelf.R
import com.stiehl.bookshelf.adapters.ChapterAdapter
import com.stiehl.bookshelf.api.results.GetChaptersResult
import com.stiehl.bookshelf.model.Book
import com.stiehl.bookshelf.util.BookServiceGenerator
import kotlinx.android.synthetic.main.fragment_book.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookFragment : Fragment() {
    private lateinit var book: Book

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)

        book = arguments?.getSerializable("book") as Book
        view.lbBookTitle.text = book.title
        view.lbBookAuthor.text = book.author
        view.lbBookEdition.text = book.edition.toString()
        view.lbBookYear.text = book.year.toString()
        view.lbBookPublisher.text = book.publisher

        loadChapters()

        return view
    }

    private fun loadChapters() {
        val service = BookServiceGenerator.getService()
        service.getChapters(book.id).enqueue(object : Callback<GetChaptersResult> {
            override fun onFailure(call: Call<GetChaptersResult>, t: Throwable) {}

            override fun onResponse(
                call: Call<GetChaptersResult>,
                response: Response<GetChaptersResult>
            ) {
                book.chapters = response.body()!!.chapters
                showChapters()
            }
        })
    }

    private fun showChapters() {
        val adapter = ChapterAdapter(book)
        val view = view as View
        view.listChapters.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        view.listChapters.adapter = adapter
    }
}