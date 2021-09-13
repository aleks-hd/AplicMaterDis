package com.hfad.aplicmaterdis.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hfad.aplicmaterdis.R
import com.hfad.aplicmaterdis.databinding.FragmentMainBinding
import com.hfad.aplicmaterdis.repository.PictureData
import com.hfad.aplicmaterdis.viewModel.PictureViewModel


class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: PictureViewModel
    private lateinit var bottomSheetBehavir: BottomSheetBehavior<ConstraintLayout>


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(PictureViewModel::class.java)
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.app_bar_fav -> Toast.makeText(context, "Избранное", Toast.LENGTH_SHORT).show()
            R.id.app_bar_search -> openFragment()
        }

        return super.onOptionsItemSelected(item)

    }

    private fun openFragment() {
       activity?.supportFragmentManager?.beginTransaction()
           ?.add(R.id.chip_container, ChipsFragment.newInstance())
           ?.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer { render(it) })
        setBottomAppBar(view)
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        view?.let { setBottomSheetBehavior(it.findViewById(R.id.bottom_sheet_behavor)) }
    }

    private fun setBottomAppBar(view: View?) {
    val context = activity as MainActivity
        context.setSupportActionBar(view?.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout){
        bottomSheetBehavir = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavir.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun render(data: PictureData?) {
        when(data){
            is PictureData.Success -> {
                val serverResponseData = data.serverResponse
                val url = serverResponseData.url
                showPictures(url)
                Toast.makeText(context,url, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showPictures(url: String?) {
        binding.imageView.load(url)
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
