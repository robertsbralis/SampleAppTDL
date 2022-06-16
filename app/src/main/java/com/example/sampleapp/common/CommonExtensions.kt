package com.example.sampleapp.common

import android.content.Context
import android.widget.Toast

fun Context.showMessage(count: String, limit: Int) {
    Toast.makeText(this,"Image count is $count and limit is $limit", Toast.LENGTH_SHORT).show()
}