package com.example.mvvmsecondmodel.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsecondmodel.R
import com.example.mvvmsecondmodel.data.lokal.entity.Movie
import com.example.mvvmsecondmodel.data.respository.Resource
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel()
    private var listmovie : MutableList<Movie> = mutableListOf()
    private var groupAdapter: GroupAdapter<ViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initrv()

        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")

        vm.observerListMovie().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    it.loadingData?.let { movies ->
//                   if (listmovie.isNotEmpty()) listmovie.clear()
                        loading.show()

                        segaran.isRefreshing = false
                    }
                }
                is Resource.Success -> {
                    it.successData?.let { movies ->
                        if (listmovie.isNotEmpty()) listmovie.clear()
                        loading.dismiss()
                        groupAdapter.clear()

                        movies.forEach {
                            Timber.d("Berhasil" + it.movie_id)
                            groupAdapter.add(MovieItem(it))
                        }
//                   listmovie.addAll(movies)
                        groupAdapter.notifyDataSetChanged()
                        segaran.isRefreshing = false
                    }
                }
                is Resource.Error -> {
                    loading.dismiss()
                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                    Timber.e(it.msg)
                }
            }
        })
        vm.getListMovie()

        segaran.setOnRefreshListener {
            vm.getListMovie()
        }
    }

    private fun initrv() {
        movie_rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groupAdapter
        }
    }
}