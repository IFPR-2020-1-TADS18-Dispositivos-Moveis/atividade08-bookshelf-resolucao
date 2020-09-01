package com.stiehl.bookshelf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stiehl.bookshelf.fragments.BookFragment
import com.stiehl.bookshelf.fragments.BookListFragment
import com.stiehl.bookshelf.fragments.BookListFragmentListener
import com.stiehl.bookshelf.model.Book
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookListFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (complexLayout) {
            supportFragmentManager.beginTransaction().apply {
                val bookListFragment = BookListFragment(this@MainActivity)
                replace(R.id.bookListFragmentContainer, bookListFragment)
                commit()
            }
        }
    }

    override fun onBookSelected(book: Book) {
        if (complexLayout) {
            supportFragmentManager.beginTransaction().apply {
                val bookFragment = BookFragment()
                val bundle = Bundle()
                bundle.putSerializable("book", book)

                bookFragment.arguments = bundle
                replace(R.id.bookFragmentContainer, bookFragment)
                addToBackStack(null)
                commit()
            }
        }
    }

    private val complexLayout
        get() = bookFragmentContainer != null
}