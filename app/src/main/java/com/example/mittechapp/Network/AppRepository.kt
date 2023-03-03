package com.example.mittechapp.Network

class AppRepository {
    suspend fun sendRequestToTheServer() =
        RetrofitInstance.callPopulationApi.callRetrofit()
}