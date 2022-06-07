package com.example.sampleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.ListItemBinding

class DataAdapter(private val items: List<String>, private val callback: (selected: String) -> Unit) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding

    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        binding.tvText.apply {
            text = item
            setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

}
