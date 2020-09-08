package com.vincentwang.zoo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vincentwang.zoo.R
import com.vincentwang.zoo.ui.intro.IntroFragment
import com.vincentwang.zoo.util.startFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(R.id.container,
            IntroFragment()
        )
    }
}