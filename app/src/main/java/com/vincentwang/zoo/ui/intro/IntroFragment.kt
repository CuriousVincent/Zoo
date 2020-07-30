package com.vincentwang.zoo.ui.intro

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentIntroBinding
import com.vincentwang.zoo.ui.plant.PlantFragData
import com.vincentwang.zoo.ui.plant.PlantFragment
import com.vincentwang.zoo.util.startFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_intro.*
import kotlinx.android.synthetic.main.item_intro.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class IntroFragment : BaseFragment() {

    lateinit var binding: FragmentIntroBinding
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

        vm.setAdapter.observe(this, Observer {
            binding.introList.adapter = IntroListAdapter(it) { view, position ->
                view.setOnClickListener {
                    vm.getPlant(position)
                }
            }
        })
        vm.goPlantFragment.observe(this, Observer {
            startFragment(R.id.container, this, PlantFragment.newInstance(it))
        })

        vm.showErrorDialog.observe(this, Observer {
            val builder: AlertDialog.Builder = AlertDialog.Builder(act)
            builder.setMessage("API ERROR")
            builder.setTitle("API ERROR")
            builder.setPositiveButton("Confirm") { dialog, which -> dialog.dismiss() }
            val dialog = builder.create()
            dialog.show()
        })

    }
}