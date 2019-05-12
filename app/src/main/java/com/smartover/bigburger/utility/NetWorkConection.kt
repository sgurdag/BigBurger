package com.smartover.bigburger.utility

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

class NetWorkConection {

    companion object {
        @SuppressLint("MissingPermission")
        fun isNetworkConnected(context: Context?): Boolean {

            var connectionManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectionManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}
