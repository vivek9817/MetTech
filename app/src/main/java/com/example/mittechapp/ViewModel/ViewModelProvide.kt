package com.example.mittechapp.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mittechapp.CommonUtils.MitApplication
import com.example.mittechapp.Network.AppRepository

class ViewModelProvide(
    val app: MitApplication,
    val appiRepository: AppRepository,
    val ctx : Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommonViewModel::class.java))
            return CommonViewModel(app, appiRepository,ctx) as T

        throw IllegalArgumentException("Unknown class name")
    }
}