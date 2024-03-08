package com.bionichamza.quabionicapp.ui.fragments.homebio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bionichamza.quabionicapp.adapters.HomeProsAdapter
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

    private val mAdapter by lazy { HomeProsAdapter() }

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

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        } , viewLifecycleOwner , Lifecycle.State.RESUMED)

        setupRecyclerView()

        prostheticsViewModel.readBackOnline.observe(viewLifecycleOwner) {
            prostheticsViewModel.backOnline = it
        }

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                networkListener = NetworkListener()
                networkListener.checkNetworkAvailability(requireContext())
                    .collect{ status ->
                        Log.d("NetworkListener" , status.toString())
                        prostheticsViewModel.networkStatus = status
                        prostheticsViewModel.showNetworkStatus()
                        readDatabase()
                    }
            }
        }

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.recyclerViewHomeBio.adapter = mAdapter
        binding.recyclerViewHomeBio.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchApiData(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun readDatabase(){
        lifecycleScope.launch {
            mainViewModel.readProstheticsInfo.observeOnce(viewLifecycleOwner) { database->
                if (database.isNotEmpty() && dataRequested) {
                    Log.d("HomeProsFragment" , "readDatabase called")
                    mAdapter.setData(database[0].prostheticsInfo)
                }
                else{
                    Log.d("HomeProsFragment" , "requestApi called")
                    if (!dataRequested) {
                        requestApiData()
                        dataRequested = true
                    }
                }
            }
        }
    }

    private fun requestApiData() {
        Log.d("ProstheticsFragment" ,"requestApiCalled")
        mainViewModel.getProstheticsInfo(prostheticsViewModel.applyQueries())
        mainViewModel.prostheticsResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    //response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {}
            }
        }
    }

    private fun searchApiData(searchQuery : String){
        mainViewModel.searchProsthetics(prostheticsViewModel.applySearchQuery(searchQuery))
        mainViewModel.searchedProstheticsResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    val prosthetics = response.data
                    //prosthetics?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {}
            }
        }
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readProstheticsInfo.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].prostheticsInfo)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}