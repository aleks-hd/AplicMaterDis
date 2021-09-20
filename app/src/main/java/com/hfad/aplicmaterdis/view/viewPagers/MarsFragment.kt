package com.hfad.aplicmaterdis.view.viewPagers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentMarsBinding
import com.hfad.aplicmaterdis.repository.PictureData
import com.hfad.aplicmaterdis.viewModel.PictureViewModel

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: PictureViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PictureViewModel::class.java)
       _binding = FragmentMarsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(data: PictureData?) {
        when (data) {
            is PictureData.Success -> {
                val serverResponseData = data.serverResponse
                val url = serverResponseData.url
                binding.imageView.load(url)
            }
            is PictureData.Loading -> {
                binding.imageView.load(R.drawable.progress_animation)
            }
            is PictureData.Error -> {
                binding.imageView.load(R.drawable.error_load)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =MarsFragment()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}