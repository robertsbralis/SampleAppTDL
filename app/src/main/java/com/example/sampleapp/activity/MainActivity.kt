package com.example.sampleapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sampleapp.App
import com.example.sampleapp.adapters.DataAdapter
import com.example.sampleapp.adapters.ImageAdapter
import com.example.sampleapp.common.lazyViewModel
import com.example.sampleapp.common.showMessage
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.repository.DataRepositoryImpl
import com.example.sampleapp.viewModel.DataState
import com.example.sampleapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var repository: DataRepositoryImpl

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazyViewModel({ application as App }, { MainViewModel(repository) })

    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter { url ->
            PreviewActivity.start(this, url)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.component.inject(this)

        observe()
        init()
    }

    private fun observe() {
        // Live data
        viewModel.images.observe(this) { images ->
            println("Items received")
            imageAdapter.setDataDiffUtil(images.take(viewModel.getLimit(binding.editText.text.toString())))
        }

        viewModel.amountOfResult.observe(this) { amount ->
            println("Result amount $amount")
            text_view.text = amount.toString()
        }

        // State flow
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is DataState.Success -> imageAdapter.setDataDiffUtil(state.data.take(5))
                    else -> {
                        // Empty
                    }
                }
            }
        }
    }

    private fun init() {
        binding.rvData.apply {
            adapter = DataAdapter(listOf("corgi", "chow", "hound")) { selected ->
                viewModel.getImages(selected)
            }
        }

        binding.rvImages.apply {
            adapter = imageAdapter
        }

        binding.imageView.setOnClickListener {
            showMessage(viewModel.amountOfResult.value.toString(),viewModel.getLimit(binding.editText.text.toString()))
        }
    }

}
