package com.smartover.bigburger.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.smartover.bigburger.R
import com.smartover.bigburger.ui.fragment.CartFragment
import com.smartover.bigburger.ui.fragment.ProductListFragment
import com.smartover.bigburger.ui.fragment.ProgressFragment

class MainActivity : AppCompatActivity() {


    companion object {
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        pushProductListFragment()

    }

    fun pushProgressFragment() {
        supportFragmentManager.inTransaction {
            add(R.id.main_container, ProgressFragment(), ProgressFragment.TAG)
        }
    }

    fun removeFragment(tag:String) {
        val progressFragment = supportFragmentManager.findFragmentByTag(tag)

        supportFragmentManager.inTransaction {
            if (progressFragment != null) {
                remove(progressFragment)
            }
        }
    }

    fun pushProductListFragment() {
        supportFragmentManager.inTransaction {
            add(R.id.main_container, ProductListFragment(), ProductListFragment.TAG)
        }
    }

    fun pushCartFragment() {
        supportFragmentManager.inTransaction {
            add(R.id.main_container, CartFragment(), CartFragment.TAG)
        }
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}
