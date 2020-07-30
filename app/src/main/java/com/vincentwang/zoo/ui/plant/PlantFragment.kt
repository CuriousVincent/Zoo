package com.vincentwang.zoo.ui.plant

import android.R
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentPlantBinding
import com.vincentwang.zoo.ui.intro.Result
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.concurrent.fixedRateTimer

class PlantFragment : BaseFragment() {
    lateinit var binding: FragmentPlantBinding
    private val vm by viewModel<PlantVM>()

    companion object {
        const val ARGUMENT_PLANT_FRAG_DATA = "argPlantFragData"

        fun newInstance(fragData: PlantFragData): PlantFragment {
            val fragment = PlantFragment()
            val args = Bundle()
            args.putParcelable(ARGUMENT_PLANT_FRAG_DATA, fragData)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantBinding.inflate(inflater, container, false).apply {
            vm = this@PlantFragment.vm
            lifecycleOwner = this@PlantFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        act.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            parentFragmentManager.popBackStack()
        })
        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        act.supportActionBar?.setDisplayShowHomeEnabled(true)
        getBundle()
        initView()
    }

    private fun getBundle() {
        arguments?.apply {
            vm.getData(getParcelable(ARGUMENT_PLANT_FRAG_DATA))
        }
    }
    private fun initView(){
        vm.setTitle.observe(this, Observer {
            toolbar.title = it
        })
    }
}

@Parcelize
data class PlantFragData(
    val plantData: PlantData,
    val introData:Result
) : Parcelable