package com.example.mittechapp.CommonUtils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

object CommonUtils {

    //Network connection check
    fun hasInternetConnection(ctx: Context): Boolean {
        val connectivityManager = ctx.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    fun snakeBarPopUp(view: ViewGroup, title: String) {
        val snackbar = Snackbar
            .make(view, title, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun initializeRecyclerView(view_recyler: RecyclerView, ctx: Context): RecyclerView {
        view_recyler!!.layoutManager = LinearLayoutManager(
            ctx,
            LinearLayoutManager.VERTICAL,
            false
        )
        view_recyler.setHasFixedSize(true)
        view_recyler.addItemDecoration(
            DividerItemDecoration(
                ctx,
                DividerItemDecoration.VERTICAL
            )
        )
        return view_recyler
    }

    inline fun <reified T> checkTypeCast(anything: Any): T? {
        return anything as? T
    }

}