package com.example.sampleapp.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleapp.databinding.FragmentPreviewBinding
import com.squareup.picasso.Picasso

class PreviewFragment : Fragment() {

    private lateinit var binding: FragmentPreviewBinding

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            url = it.getString(FRAGMENT_EXTRA_URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPreviewBinding.inflate(inflater, container, false)
        init(url)
        return binding.root
    }

    private fun init(url: String?) {
        url?.let {
            Picasso.get().load(it).fit().centerCrop().into(binding.ivImage)
        }
    }

    companion object {
        const val TAG = "PreviewFragment"
        private const val FRAGMENT_EXTRA_URL = "fragment_extra_image_url"

        fun create(url: String) : PreviewFragment = PreviewFragment().apply {
            Bundle().apply {
                putString(FRAGMENT_EXTRA_URL, url)
                arguments = this
            }
        }
    }
}
