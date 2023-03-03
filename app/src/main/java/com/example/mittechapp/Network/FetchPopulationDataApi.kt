package com.example.mittechapp.Network

import com.example.mittechapp.Model.PopulationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface FetchPopulationDataApi {
    @GET("/api/data?drilldowns=Nation&measures=Population")
    suspend fun callRetrofit() : Response<PopulationResponse>
}