package com.example.mittechapp.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.CommonUtils.Events
import com.example.mittechapp.CommonUtils.MitApplication
import com.example.mittechapp.CommonUtils.Resource
import com.example.mittechapp.Model.PopulationFetchRequest
import com.example.mittechapp.Model.PopulationResponse
import com.example.mittechapp.Network.AppRepository
import com.example.mittechapp.Network.FetchPopulationDataApi
import com.example.mittechapp.R
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CommonViewModel(
    app: Application,
    private val appiRepository: AppRepository,
    ctx: Context
) : AndroidViewModel(app) {

    private val setresponse =
        MutableLiveData<Events<Resource<PopulationResponse>>>()
    val getresponse: MutableLiveData<Events<Resource<PopulationResponse>>> =
        setresponse

    fun setRequestToTheServer() = viewModelScope.launch {
        callServer()
    }

    private suspend fun callServer() {
        setresponse.postValue(Events(Resource.Loading()))
        try {
            if (CommonUtils.hasInternetConnection(getApplication<MitApplication>())) {
                var responses = appiRepository.sendRequestToTheServer()
                setresponse.postValue(handleResponse(responses))
            } else {
                setresponse.postValue(
                    Events(
                        Resource.Error(
                            getApplication<Application>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        } catch (err: Throwable) {
            when (err) {
                is IOException -> {
                    setresponse.postValue(
                        Events(
                            Resource.Error(
                                getApplication<Application>().getString(
                                    R.string.network_failure
                                )
                            )
                        )
                    )
                }
                else -> {
                    setresponse.postValue(
                        Events(
                            Resource.Error(
                                getApplication<Application>().getString(
                                    R.string.conversion_error
                                )
                            )
                        )
                    )
                }
            }
        }
    }

    private fun handleResponse(response: Response<PopulationResponse>): Events<Resource<PopulationResponse>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.e("Called Data", resultResponse.toString())
                return Events(Resource.Success(resultResponse))
            }
        }
        return Events(Resource.Error(response.message()))
    }
}