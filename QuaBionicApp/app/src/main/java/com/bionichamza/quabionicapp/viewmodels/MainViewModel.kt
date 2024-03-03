package com.bionichamza.quabionicapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.*
import com.bionichamza.quabionicapp.data.Repository
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : Repository,
    application: Application
) : AndroidViewModel(application) {

    /** Room Database Process */

    val readProsthetics : LiveData<List<ProstheticsEntity>> = repository.local.readProsthetics().asLiveData()
    val readFavoriteProsthetics : LiveData<List<FavoriteEntity>> = repository.local.readFavoriteProsthetics().asLiveData()
    val readInspiration : LiveData<List<InspirationEntity>> = repository.local.readInspiration().asLiveData()

    private fun insertProsthetics(prostheticsEntity : ProstheticsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertProsthetics(prostheticsEntity)
        }

    fun insertFavoriteProsthetics(favoriteEntity: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFavoriteProsthetics(favoriteEntity)
        }

    private fun insertInspiration(inspirationEntity: InspirationEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertInspiration(inspirationEntity)
        }

    fun deleteFavoriteProsthetic(favoriteEntity: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteProsthetic(favoriteEntity)
        }

    fun deleteAllFavoriteProsthetics() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllFavoriteProsthetics()
        }

    /** Retrofit Process */

    var prostheticsResponse : MutableLiveData<NetworkResult<Prosthetics>> = MutableLiveData()
    var searchProstheticsResponse : MutableLiveData<NetworkResult<Prosthetics>> = MutableLiveData()
    var inspirationResponse : MutableLiveData<NetworkResult<InspirationWord>> = MutableLiveData()

    fun getProsthetics(queries : Map<String , String>) = viewModelScope.launch {
        getProstheticsSafeCall(queries)
    }

    fun searchProsthetics(searchQuery : Map<String , String>) = viewModelScope.launch {
        // TODO: Wait safe call function please.
    }

    fun getInspiration(apiKey : String) = viewModelScope.launch {
        // TODO: Wait safe call function please.
    }

    private suspend fun getProstheticsSafeCall(queries : Map<String , String>) {
        prostheticsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getProsthetics(queries)
                prostheticsResponse.value = handleProstheticsResponse(response)

                val prosthetics = prostheticsResponse.value!!.data
                if (prosthetics != null) {
                    offlineCacheProsthetics(prosthetics)
                }
            }catch (e : Exception) {
                prostheticsResponse.value = NetworkResult.Error("Prosthetics not found")
            }
        }else {
            prostheticsResponse.value = NetworkResult.Error("No Internet Connection!")
        }
    }

    private fun offlineCacheProsthetics(prosthetics: Prosthetics) {
        val prostheticsEntity = ProstheticsEntity(prosthetics)
        insertProsthetics(prostheticsEntity)
    }

    private fun offlineCacheInspiration(inspirationWord: InspirationWord) {
        val inspirationEntity = InspirationEntity(inspirationWord)
        insertInspiration(inspirationEntity)
    }

    private fun handleProstheticsResponse(response : Response<Prosthetics>) : NetworkResult<Prosthetics> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API key limited")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Prosthetics not found!")
            }
            response.isSuccessful -> {
                val prosthetics = response.body()
                return NetworkResult.Success(prosthetics!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection() : Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}