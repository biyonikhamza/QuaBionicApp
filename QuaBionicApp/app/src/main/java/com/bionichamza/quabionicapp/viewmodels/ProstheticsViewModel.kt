package com.bionichamza.quabionicapp.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bionichamza.quabionicapp.data.DataStoreRepository
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

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if {
            if (backOnline) {
                Toast.makeText(getApplication(), "Welcome Bionic", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}