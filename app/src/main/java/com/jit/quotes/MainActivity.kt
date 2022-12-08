package com.jit.quotes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jit.quotes.adapter.CustomAdapter
import com.jit.quotes.data.Quotes
import com.jit.quotes.databinding.ActivityMainBinding
import com.jit.quotes.viewModel.QuoteViewModel
import com.jit.quotes.viewModel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: QuoteViewModel
    private lateinit var  binding:ActivityMainBinding
    private var progressDialog:ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as QuoteApplication).repository

        viewModel = ViewModelProvider(this,QuoteViewModelFactory(repository))
            .get(QuoteViewModel::class.java)

        attachLoader()

        val adapter = CustomAdapter()

        viewModel.quotes.observe(this){
            if(it != null) {
                adapter.bind(it)
                progressDialog?.dismiss()
                binding.recyclerView.adapter = adapter
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun attachLoader() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Please Wait")
        progressDialog?.setMessage("Loading....")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
}