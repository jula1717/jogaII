package com.example.jogaii.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jogaii.R
import com.example.jogaii.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            txtSanskritName.text = viewModel.sanskritName
            txtAsanaType.text = viewModel.type
            //TODO: progressBar ustawić na wartość difficulty i ikonę odpowiednio, w on create img też na pocztątku powinien być chyba invisible
            txtDescription.text = viewModel.description
            imgAsana.setImageResource(viewModel.imgRes)
            txtName.text = viewModel.asanaName

        }

    }
}

