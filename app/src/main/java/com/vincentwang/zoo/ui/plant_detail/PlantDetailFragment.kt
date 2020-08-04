package com.vincentwang.zoo.ui.plant_detail

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentPlantDetailBinding
import com.vincentwang.zoo.ui.plant.ResultX
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlantDetailFragment : BaseFragment() {
    lateinit var binding: FragmentPlantDetailBinding

        private val vm by viewModel<PlantDetailVM>()



    companion object {
        const val ARGUMENT_PLANT_DETAIL_FRAG_DATA = "argPlantDetailFragData"
        fun newInstance(fragData: PlantDetailFragData): PlantDetailFragment {
            val fragment = PlantDetailFragment()
            val args = Bundle()
            args.putParcelable(PlantDetailFragment.ARGUMENT_PLANT_DETAIL_FRAG_DATA, fragData)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.change_image_transform)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false).apply {
            vm = this@PlantDetailFragment.vm
            lifecycleOwner = this@PlantDetailFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        act.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        act.supportActionBar?.setDisplayShowHomeEnabled(true)
        arguments?.apply {

            val data: PlantDetailFragData? =
                getParcelable(PlantDetailFragment.ARGUMENT_PLANT_DETAIL_FRAG_DATA)
            data?.apply {
                toolbar.title = this.data.F_Name_En
                vm.setData(this)
            }
        }
    }

}

@Parcelize
data class PlantDetailFragData(
    val data: ResultX
) : Parcelable