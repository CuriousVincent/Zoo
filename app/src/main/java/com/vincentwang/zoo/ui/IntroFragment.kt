package com.vincentwang.zoo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincentwang.zoo.R
import kotlinx.android.synthetic.main.fragment_intro.*

/**
 * A fragment representing a list of Items.
 */
class IntroFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        introList.layoutManager =  LinearLayoutManager(context)

        return view
    }
}