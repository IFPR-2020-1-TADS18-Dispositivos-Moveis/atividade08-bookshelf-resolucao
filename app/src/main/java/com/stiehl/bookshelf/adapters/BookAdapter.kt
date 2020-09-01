package com.stiehl.bookshelf.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stiehl.bookshelf.R
import com.stiehl.bookshelf.model.Book
import com.stiehl.bookshelf.util.BookServiceGenerator
import kotlinx.android.synthetic.main.item_book.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookAdapter(private val listener: BookAdapterListener) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var books = mutableListOf<Book>()
    private val service = BookServiceGenerator.getService()

    init {
        service.getAll().enqueue(object : Callback<List<Book>> {
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {}

            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                books = response.body()!!.toMutableList()
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemViewType(position: Int) = R.layout.item_book

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.fillView(book)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(book: Book) {
            itemView.lbBookItemTitle.text = book.title
            itemView.lbBookItemAuthor.text = book.author

            itemView.setOnClickListener {
                listener.onBookSelected(book)
            }
        }
    }
}