package com.vincentwang.zoo.ui.plant_detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vincentwang.zoo.R
import com.vincentwang.zoo.base.BaseFragment
import com.vincentwang.zoo.databinding.FragmentPlantDetailBinding
import com.vincentwang.zoo.ui.plant.ResultX
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_intro.*

class PlantDetailFragment : BaseFragment() {
    lateinit var binding: FragmentPlantDetailBinding

    private val vm by viewModels<PlantDetailVM>()


    companion object {
        const val ARGUMENT_PLANT_DETAIL_FRAG_DATA = "argPlantDetailFragData"
        fun newInstance(fragData: PlantDetailFragData): PlantDetailFragment {
            val fragment = PlantDetailFragment()
            val args = Bundle()
            args.putParcelable(ARGUMENT_PLANT_DETAIL_FRAG_DATA, fragData)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.change_image_transform)
        sharedElementReturnTransition =
            TransitionInflater.from(act).inflateTransition(R.transition.change_image_transform)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailBinding.inflate(inflater, container, false).apply {
            vm = this@PlantDetailFragment.vm
            lifecycleOwner = this@PlantDetailFragment
        }
        postponeEnterTransition()
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
        arguments?.getParcelable<PlantDetailFragData>(ARGUMENT_PLANT_DETAIL_FRAG_DATA)
            ?.apply {
                toolbar.title = this.data.F_Name_En
                vm.setData(this, object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
            }
    }

}

@Parcelize
data class PlantDetailFragData(
    val data: ResultX
) : Parcelable