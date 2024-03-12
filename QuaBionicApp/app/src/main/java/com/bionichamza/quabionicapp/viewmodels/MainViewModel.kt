package com.bionichamza.quabionicapp.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.JsonToken
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.*
import com.bionichamza.quabionicapp.data.Repository
import com.bionichamza.quabionicapp.data.database.entities.FavoriteEntity
import com.bionichamza.quabionicapp.data.database.entities.InspirationEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsEntity
import com.bionichamza.quabionicapp.data.database.entities.ProstheticsInfoEntity
import com.bionichamza.quabionicapp.models.InspirationWord
import com.bionichamza.quabionicapp.models.Prosthetics
import com.bionichamza.quabionicapp.models.ProstheticsInfo
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

    val readProstheticsInfo : LiveData<List<ProstheticsInfoEntity>> = repository.local.readProstheticsInfo().asLiveData()
    val readProsthetics : LiveData<List<ProstheticsEntity>> = repository.local.readProsthetics().asLiveData()
    val readFavoriteProsthetics : LiveData<List<FavoriteEntity>> = repository.local.readFavoriteProsthetics().asLiveData()
    val readInspiration : LiveData<List<InspirationEntity>> = repository.local.readInspiration().asLiveData()

    private fun insertProstheticsInfo(prostheticsInfoEntity : ProstheticsInfoEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertProstheticsInfo(prostheticsInfoEntity)
        }
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

    var prostheticsInfoResponse : MutableLiveData<NetworkResult<ProstheticsInfo>> = MutableLiveData()
    var prostheticsResponse : MutableLiveData<NetworkResult<Prosthetics>> = MutableLiveData()
    var searchedProstheticsInfoResponse : MutableLiveData<NetworkResult<ProstheticsInfo>> = MutableLiveData()
    var searchedProstheticsResponse : MutableLiveData<NetworkResult<Prosthetics>> = MutableLiveData()
    var inspirationResponse : MutableLiveData<NetworkResult<InspirationWord>> = MutableLiveData()

    fun getProstheticsInfo(queries: Map<String, String>) = viewModelScope.launch {
        getProstheticsInfoSafeCall(queries)
    }
    fun getProsthetics(queries : Map<String , String>) = viewModelScope.launch {
        getProstheticsSafeCall(queries)
    }

    fun searchProstheticsInfo(searchQuery : Map<String , String>) = viewModelScope.launch {
        searchProstheticsInfoSafeCall(searchQuery)
    }

    fun searchProsthetics(searchQuery : Map<String , String>) = viewModelScope.launch {
        searchProstheticsSafeCall(searchQuery)
    }

    fun getInspiration(queries: Map<String, String>) = viewModelScope.launch {
        getInspirationSafeCall(queries)
    }

    private suspend fun getProstheticsInfoSafeCall(queries : Map<String , String>) {
        prostheticsInfoResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getProstheticsInfo(queries)
                prostheticsInfoResponse.value = handleProstheticsInfoResponse(response)

                val prostheticsInfo = prostheticsInfoResponse.value!!.data
                if (prostheticsInfo != null){
                    offlineCacheProstheticsInfo(prostheticsInfo)
                }
            }catch (e : Exception) {
                prostheticsInfoResponse.value = NetworkResult.Error("Prosthetics info not found")
            }
        } else {
            prostheticsInfoResponse.value = NetworkResult.Error("No internet Connection!")
        }
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

    private suspend fun searchProstheticsInfoSafeCall(searchQuery: Map<String, String>) {
        searchedProstheticsInfoResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.searchProstheticsInfo(searchQuery)
                searchedProstheticsInfoResponse.value = handleProstheticsInfoResponse(response)
            }catch (e : Exception) {
                searchedProstheticsInfoResponse.value = NetworkResult.Error("Prosthetics not found")
            }
        } else {
            searchedProstheticsInfoResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private suspend fun searchProstheticsSafeCall(searchQuery : Map<String, String>) {
        searchedProstheticsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.searchProsthetics(searchQuery)
                searchedProstheticsResponse.value = handleProstheticsResponse(response)
            }catch (e : Exception) {
                searchedProstheticsResponse.value = NetworkResult.Error("Prosthetics not found!")
            }
        } else {
            searchedProstheticsResponse.value = NetworkResult.Error("No Internet Connection!")
        }
    }

    private suspend fun getInspirationSafeCall(queries: Map<String, String>) {
        inspirationResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response = repository.remote.getInspiration(queries)
                inspirationResponse.value = handleInspirationResponse(response)

                val inspiration = inspirationResponse.value!!.data
                if (inspiration != null) {
                    offlineCacheInspiration(inspiration)
                }
            }catch (e : Exception) {
                inspirationResponse.value = NetworkResult.Error("Inspiration not found")
            }
        }else {
            inspirationResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun offlineCacheProstheticsInfo(prostheticsInfo : ProstheticsInfo) {
        val prostheticsInfoEntity = ProstheticsInfoEntity(prostheticsInfo)
        insertProstheticsInfo(prostheticsInfoEntity)
    }
    private fun offlineCacheProsthetics(prosthetics: Prosthetics) {
        val prostheticsEntity = ProstheticsEntity(prosthetics)
        insertProsthetics(prostheticsEntity)
    }

    private fun offlineCacheInspiration(inspirationWord: InspirationWord) {
        val inspirationEntity = InspirationEntity(inspirationWord)
        insertInspiration(inspirationEntity)
    }

    private fun handleProstheticsInfoResponse(response : Response<ProstheticsInfo>) : NetworkResult<ProstheticsInfo> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API key limited")
            }
            response.isSuccessful -> {
                val prostheticsInfo = response.body()
                return NetworkResult.Success(prostheticsInfo!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
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

    private fun handleInspirationResponse(response: Response<InspirationWord>) : NetworkResult<InspirationWord> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                NetworkResult.Error("API key limited")
            }
            response.isSuccessful -> {
                val inspirationWord = response.body()
                NetworkResult.Success(inspirationWord!!)
            }
            else -> {
                NetworkResult.Error(response.message())
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