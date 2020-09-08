package com.vincentwang.zoo.ui.plant

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentPlantBinding
import com.vincentwang.zoo.ui.intro.Result
import com.vincentwang.zoo.ui.plant_detail.PlantDetailFragData
import com.vincentwang.zoo.ui.plant_detail.PlantDetailFragment
import com.vincentwang.zoo.util.startCustomTabsIntent
import com.vincentwang.zoo.util.startFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_intro.*
import kotlinx.android.synthetic.main.item_plant_list.view.*


class PlantFragment : BaseFragment() {
    lateinit var binding: FragmentPlantBinding
    private val vm by viewModels<PlantVM>()

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementReturnTransition = TransitionInflater.from(act)
            .inflateTransition(R.transition.change_image_transform)
        sharedElementEnterTransition = TransitionInflater.from(act)
            .inflateTransition(R.transition.change_image_transform)
        exitTransition = TransitionInflater.from(act)
            .inflateTransition(R.transition.exit_transition)
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
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
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

    private fun initView() {
        vm.setTitle.observe(this, Observer {
            toolbar.title = it
        })
        vm.setAdapter.observe(this, Observer {
            binding.plantList.adapter = PlantAdapter(it) { view, position ->
                setOnClickListener(view) {
                    when (view.id) {
                        R.id.web -> {
                            vm.getShowWeb(position)
                        }
                        R.id.itemContainer -> {
                            vm.goDetail(view.image,position)
                        }
                    }
                }

            }
        })
        vm.showWebView.observe(this, Observer {
            context?.startCustomTabsIntent(it)
        })
        vm.goPlantDetail.observe(this, Observer {pair->
            val fragData = pair.second
            val imageView = pair.first
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(imageView,fragData.data.F_Pic01_URL?:"http://www.zoo.gov.tw/iTAP/04_Plant/Lythraceae/subcostata/subcostata_1.jpg" )
                .addToBackStack(null)
                .add(R.id.container, PlantDetailFragment.newInstance(
                    fragData
                ), PlantDetailFragment::class.java.simpleName)
                .hide(this)
                .commit()
        })
    }
}

@Parcelize
data class PlantFragData(
    val plantData: PlantData,
    val introData: Result
) : Parcelable