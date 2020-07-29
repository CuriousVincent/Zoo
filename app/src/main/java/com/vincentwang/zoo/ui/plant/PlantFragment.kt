package com.vincentwang.zoo.ui.plant

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentIntroBinding
import com.vincentwang.zoo.databinding.FragmentPlantBinding
import com.vincentwang.zoo.ui.intro.IntroVM
import kotlinx.android.parcel.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        getBundle()
    }

    fun getBundle() {
        arguments?.apply {
            vm.getData(getParcelable(ARGUMENT_PLANT_FRAG_DATA))
        }
    }
}

@Parcelize
data class PlantFragData(
    val data: PlantData
) : Parcelable