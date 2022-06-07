package com.example.sampleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.ListImageItemBinding
import com.squareup.picasso.Picasso

class ImageAdapter(private val callback: (selected: String) -> Unit) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private lateinit var binding: ListImageItemBinding

    private var items: MutableList<String> = mutableListOf()

    class ViewHolder(binding: ListImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        binding.ivImage.apply {

            Picasso.get().load(item).fit().centerCrop().into(this)

            setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: List<String>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}
