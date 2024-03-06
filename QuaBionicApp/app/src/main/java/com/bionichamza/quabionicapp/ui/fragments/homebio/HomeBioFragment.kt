package com.bionichamza.quabionicapp.ui.fragments.homebio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bionichamza.quabionicapp.util.NetworkListener
import com.bionichamza.quabionicapp.util.NetworkResult
import com.bionichamza.quabionicapp.util.observeOnce
import com.bionichamza.quabionicapp.viewmodels.MainViewModel
import com.bionichamza.quabionicapp.viewmodels.ProstheticsViewModel
import com.example.quabionicapp.R
import com.example.quabionicapp.databinding.FragmentHomeProsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeBioFragment : Fragment(), SearchView.OnQueryTextListener {

    private var dataRequested = false

    private var _binding : FragmentHomeProsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel : MainViewModel
    private lateinit var prostheticsViewModel : ProstheticsViewModel

    private lateinit var networkListener : NetworkListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider((requireActivity()))[MainViewModel::class.java]
        prostheticsViewModel = ViewModelProvider(requireActivity())[ProstheticsViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeProsBinding.inflate(inflater , container , false)

        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_bio_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@HomeBioFragment)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            //TODO SearchApiData function later inshaallah make
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun readDatabase(){
        // TODO inshaallah later
    }

    private fun requestApiData() {
        Log.d("ProstheticsFragment" ,"requestApiCalled")
        mainViewModel.getProsthetics(prostheticsViewModel.applyQueries())
        mainViewModel.prostheticsResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    // TODO i need mAdapter for set data
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}