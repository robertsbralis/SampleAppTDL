package com.example.sampleapp.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentNewBinding

class NewFragment : Fragment() {

    private lateinit var binding: FragmentNewBinding
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
        binding = FragmentNewBinding.inflate(inflater, container, false)
        binding.tvBack.background = this.context?.let { ContextCompat.getDrawable(it, R.drawable.ic_baseline_arrow_back_24) }
        fragManager = this.parentFragmentManager
        observe()
        return binding.root
    }

    private fun observe(){
        binding.tvBack.setOnClickListener {
            url.let {
                fragManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, PreviewFragment.create(url!!, checkboxState), PreviewFragment.TAG)
                    .commit()
            }
        }

        binding.tvExit.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        const val TAG = "NewFragment"
        private const val FRAGMENT_EXTRA_URL = "fragment_extra_image_url"
        private const val FRAGMENT_CHECKBOX_STATE = "fragment_checkbox_state"

        fun create(url: String, checkboxState: Boolean?) : NewFragment = NewFragment().apply {
            Bundle().apply {
                putString(FRAGMENT_EXTRA_URL, url)
                putBoolean(FRAGMENT_CHECKBOX_STATE, checkboxState!!)
                arguments = this
            }
        }
    }
}