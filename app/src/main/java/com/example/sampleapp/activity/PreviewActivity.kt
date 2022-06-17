package com.example.sampleapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.R
import com.example.sampleapp.activity.fragments.PreviewFragment
import com.example.sampleapp.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

   private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.preview_title)

        init(intent?.getStringExtra(EXTRA_URL), intent?.getBooleanExtra(CHECKBOX_STATE, false))
    }

    private fun init(url: String?, checkboxState: Boolean?) {
        if (url.isNullOrEmpty()) return
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, PreviewFragment.create(url, checkboxState), PreviewFragment.TAG)
            .commit()
    }


    companion object {
        private const val EXTRA_URL = "extra_image_url"
        private const val CHECKBOX_STATE = "checkbox_state"

        fun start(activity: Activity, url: String, checkboxState: Boolean) {
            activity.startActivity(Intent(activity, PreviewActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                putExtra(EXTRA_URL, url)
                putExtra(CHECKBOX_STATE, checkboxState)
            })
        }
    }
}
