package com.example.sampleapp.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import coil.load
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentPreviewBinding
import com.squareup.picasso.Picasso

class PreviewFragment : Fragment() {

    private lateinit var binding: FragmentPreviewBinding
    private lateinit var fragManager: FragmentManager

    private var url: String? = null
    private var checkboxState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            url = it.getString(FRAGMENT_EXTRA_URL)
            checkboxState = it.getBoolean(FRAGMENT_CHECKBOX_STATE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPreviewBinding.inflate(inflater, container, false)
        init(url)
        fragManager = this.parentFragmentManager
        observe()
        return binding.root
    }

    private fun init(url: String?) {
        url?.let {
            if(!checkboxState) {
                Picasso.get().load(it).fit().centerCrop().into(binding.ivImage)
                binding.tvImageLoaderType.text = "Picasso image loading library"
            } else {
                binding.ivImage.load(it) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                }
                binding.tvImageLoaderType.text = "Coil image loading library"
            }
        }
    }

    private fun observe(){
        binding.btnNext.setOnClickListener {
            url.let {
                fragManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, NewFragment.create(url!!, checkboxState), NewFragment.TAG)
                    .commit()
            }
        }
    }

    companion object {
        const val TAG = "PreviewFragment"
        private const val FRAGMENT_EXTRA_URL = "fragment_extra_image_url"
        private const val FRAGMENT_CHECKBOX_STATE = "fragment_checkbox_state"

        fun create(url: String, checkboxState: Boolean?) : PreviewFragment = PreviewFragment().apply {
            Bundle().apply {
                putString(FRAGMENT_EXTRA_URL, url)
                putBoolean(FRAGMENT_CHECKBOX_STATE, checkboxState!!)
                arguments = this
            }
        }
    }
}
