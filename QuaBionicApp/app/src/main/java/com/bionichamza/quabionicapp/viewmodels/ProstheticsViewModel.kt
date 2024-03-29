package com.bionichamza.quabionicapp.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bionichamza.quabionicapp.data.DataStoreRepository
import com.bionichamza.quabionicapp.util.Constants.Companion.API_KEY
import com.bionichamza.quabionicapp.util.Constants.Companion.QUERY_SEARCH
import com.bionichamza.quabionicapp.util.Constants.Companion.QUERY_TOKEN_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProstheticsViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository : DataStoreRepository
) : AndroidViewModel(application) {

    var networkStatus = false
    var backOnline = false

    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveBackOnline(backOnline : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    }

    fun applyQueries() : HashMap<String , String> {
        val queries : HashMap<String , String> = HashMap()

        queries[QUERY_TOKEN_KEY] = API_KEY
        return queries
    }

    fun applySearchQuery(searchQuery : String) : HashMap<String , String> {
        val queries: HashMap<String , String> = HashMap()

        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_TOKEN_KEY] = API_KEY
        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if(networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "Welcome Bionic", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}