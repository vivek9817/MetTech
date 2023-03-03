package com.example.mittechapp.Network

import com.example.mittechapp.CommonUtils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofitCalling by lazy {
            val login = HttpLoggingInterceptor()
            login.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(login)
                .build()
            Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val callPopulationApi by lazy {
            retrofitCalling.create(FetchPopulationDataApi::class.java)
        }
    }
}