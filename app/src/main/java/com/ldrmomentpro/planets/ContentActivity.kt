package com.ldrmomentpro.planets

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_layout.*

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)

        im.setImageResource(intent.getIntExtra("image", R.drawable.solar_system))
        tvTitle.text=intent.getStringExtra("title")
        tvContent.text=intent.getStringExtra("content")
    }
}