package com.lifestrim.topmovies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifestrim.topmovies.R
import com.lifestrim.topmovies.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movies_fragment.*
import kotlinx.android.synthetic.main.movies_fragment.view.*

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_fragment, container, false)
        setupRecyclerView(view)
        setupObservers()
        return view
    }

    private fun setupRecyclerView(view: View) {
        adapter = MoviesAdapter()
        view.movies_rv.layoutManager = LinearLayoutManager(activity)
        view.movies_rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.films.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE
            }
        })
    }
}