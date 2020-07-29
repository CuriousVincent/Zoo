package com.vincentwang.zoo.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentIntroBinding
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class IntroFragment : BaseFragment() {

    lateinit var binding : FragmentIntroBinding
    private val vm by viewModel<IntroVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false).apply {
            vm = this@IntroFragment.vm
            lifecycleOwner = this@IntroFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        act.setSupportActionBar(toolbar)
//        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        act.supportActionBar?.setDisplayShowHomeEnabled(true)
        vm.setAdapter.observe(this, Observer {
            binding.introList.adapter = IntroListAdapter(it) { view,position->
                view.setOnClickListener {
                    vm.getPlant(position)
                }
            }
        })
    }
}