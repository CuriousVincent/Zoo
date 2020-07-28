package com.vincentwang.zoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vincentwang.zoo.R
import com.vincentwang.zoo.util.startFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(R.id.container, IntroFragment())
    }
}