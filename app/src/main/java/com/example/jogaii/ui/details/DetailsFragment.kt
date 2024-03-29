package com.example.jogaii.ui.details

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jogaii.R
import com.example.jogaii.animations.ProgressBarAnimation
import com.example.jogaii.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            //val asana = args.asana
            txtSanskritName.text = viewModel.sanskritName
            txtAsanaType.text = viewModel.type
            val anim = ProgressBarAnimation(pbDifficultyLevel, 0f, viewModel.difficulty * 1000f)
            anim.setDuration(1000)
            pbDifficultyLevel.setMax(5 * 1000)
            pbDifficultyLevel.startAnimation(anim)
            pbDifficultyLevel.setProgress(viewModel.difficulty * 1000)
            val visibility = if (viewModel.completed) View.VISIBLE else View.INVISIBLE
            imgDone.visibility = visibility
            txtDescription.visibility = View.GONE
            txtDescription.text = viewModel.description
            imgAsana.setImageResource(viewModel.imgRes)
            txtName.text = viewModel.asanaName

            imgReverse.setOnClickListener {
                viewModel.toggleImageVisibility()
            }


            viewModel.imgVisible.observe(viewLifecycleOwner, Observer { imgVisible ->

                if(imgVisible){
                    imgAsana.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
                    txtDescription.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_out))
                }
                else{
                    imgAsana.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_out))
                    txtDescription.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
                }

                imgAsana.visibility = if (imgVisible) View.VISIBLE else View.GONE
                txtDescription.visibility = if (imgVisible) View.GONE else View.VISIBLE

            })

        }
    }

