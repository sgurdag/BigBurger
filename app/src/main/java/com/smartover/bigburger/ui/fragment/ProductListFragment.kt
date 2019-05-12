package com.smartover.bigburger.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

import com.smartover.bigburger.R
import com.smartover.bigburger.data.mvp.model.ProductListPresenterImpl
import com.smartover.bigburger.data.mvp.view.ProductListViewPresenter
import com.smartover.bigburger.data.service.response.models.Product
import com.smartover.bigburger.ui.activity.MainActivity
import com.smartover.bigburger.ui.adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.view_no_connection.*


class ProductListFragment : BaseFragment(), ProductListViewPresenter.View {

    companion object { val TAG: String = "ProductListFragment" }

    var productListPresenter: ProductListPresenterImpl? = null

    override fun getViewLayout(): Int {
        return R.layout.fragment_product_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListPresenter = ProductListPresenterImpl(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initThePageWithData()
        setUI()
    }

    private fun setUI() {
        btnTryAgain.setOnClickListener { initThePageWithData() }
    }

    private fun initThePageWithData() {
        productListPresenter!!.loadData()
    }

    override fun onProductListReceived(products: ArrayList<Product>?) {

        llNoConnection.visibility = GONE

        if (products != null) {
            setTheListWithData(products)
        } else {
            showMessage(getString(R.string.message_there_is_no_product))
        }
    }

    private fun setTheListWithData(products: ArrayList<Product>) {
        rvProductList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductAdapter(products, productListPresenter)
        }
    }

    override fun onError(throwable: Throwable) {
        showMessage(throwable.message!!)
    }

    override fun connectionError() {
        llNoConnection.visibility = VISIBLE
    }

    override fun showMessage(message: String) {
        Snackbar.make(llMainView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun gotoCart() {
        MainActivity.instance!!.pushCartFragment()
    }


    override fun onDestroy() {
        super.onDestroy()
        productListPresenter!!.onStop()
    }
}
