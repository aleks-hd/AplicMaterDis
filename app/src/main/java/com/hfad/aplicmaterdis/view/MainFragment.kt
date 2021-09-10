package com.hfad.aplicmaterdis.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentMainBinding
import com.hfad.aplicmaterdis.repository.PictureData
import com.hfad.aplicmaterdis.viewModel.PictureViewModel


class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: PictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PictureViewModel::class.java)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { render(it) })


    }

    private fun render(data: PictureData?) {
        when(data){
            is PictureData.Success -> {
                val serverResponseData = data.serverResponse
                val url = serverResponseData.url
                Toast.makeText(context,url, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
