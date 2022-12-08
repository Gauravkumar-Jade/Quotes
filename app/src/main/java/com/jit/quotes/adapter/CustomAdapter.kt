package com.jit.quotes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jit.quotes.data.Quotes
import com.jit.quotes.data.Result
import com.jit.quotes.databinding.CustomLayoutBinding


class CustomAdapter:RecyclerView.Adapter<CustomAdapter.CustomHolder>(){

    private var mQuotes:Quotes? = null

    fun bind(quotes: Quotes){
        this.mQuotes = quotes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val binding = CustomLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
       val dataPosition = holder.adapterPosition
       val quotes = mQuotes?.results?.get(dataPosition)

        with(holder){
            with(quotes){
                binding.quoteText.text = this?.content
                binding.authorText.text = this?.author
            }
        }
    }

    override fun getItemCount(): Int {
       return mQuotes?.results?.size!!
    }

    inner class CustomHolder(val binding: CustomLayoutBinding):RecyclerView.ViewHolder(binding.root){}
}