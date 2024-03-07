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
import androidx.core.view.isVisible
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

    var sayi1 = 10
    var sayi2 = 20

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

        var toplam = sayi1 + sayi2
        println(binding)

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
        showProgressBar()
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
            mainViewModel.readProsthetics.observeOnce(viewLifecycleOwner) { database->
                if (database.isNotEmpty() && dataRequested) {
                    Log.d("HomeProsFragment" , "readDatabase called")
                    //mAdapter.setData(database[0])
                    showProgressBar()
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
        mainViewModel.getProsthetics(prostheticsViewModel.applyQueries())
        mainViewModel.prostheticsResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    showProgressBar()
                    //response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    showProgressBar()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun searchApiData(searchQuery : String){
        showProgressBar()
        mainViewModel.searchProsthetics(prostheticsViewModel.applySearchQuery(searchQuery))
        mainViewModel.searchedProstheticsResponse.observe(viewLifecycleOwner) { response ->
            when(response) {
                is NetworkResult.Success -> {
                    showProgressBar()
                    val prosthetics = response.data
                    //prosthetics?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    showProgressBar()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readProsthetics.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    //mAdapter.setData(database[0].prosthetics)
                }
            }
        }
    }

    private fun showProgressBar(){
        binding.homeProsProgressBar.isVisible = true
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}