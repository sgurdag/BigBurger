package com.smartover.bigburger.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartover.bigburger.data.mvp.view.BaseView
import com.smartover.bigburger.ui.activity.MainActivity
import com.smartover.bigburger.utility.NetWorkConection

/**
 * A base fragment where we manage the functions which are common in all fragment.
 */
abstract class BaseFragment : Fragment(), BaseView {


    var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(getViewLayout(), container, false)


    override fun checkInternet(): Boolean {
        return NetWorkConection.isNetworkConnected(mContext)

    }

    override fun showProgresss() {
        MainActivity.instance?.pushProgressFragment()
    }

    override fun dismissProgresss() {
        MainActivity.instance?.removeFragment(ProgressFragment.TAG)
    }

    abstract fun showMessage(message: String)
    abstract fun getViewLayout(): Int
}