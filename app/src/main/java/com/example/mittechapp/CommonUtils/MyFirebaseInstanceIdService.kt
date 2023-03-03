package com.example.mittechapp.CommonUtils

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseInstanceIdService : FirebaseMessagingService() {

    val TAG = "PushNotifService"
    lateinit var name: String

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Token perangkat ini: ${token}")
    }

}