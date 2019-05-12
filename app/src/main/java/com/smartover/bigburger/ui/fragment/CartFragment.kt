package com.smartover.bigburger.ui.fragment


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.smartover.bigburger.R
import com.smartover.bigburger.data.mvp.model.CartPresenterImpl
import com.smartover.bigburger.data.mvp.view.CartPresenter
import com.smartover.bigburger.ui.activity.MainActivity
import com.smartover.bigburger.ui.adapter.CartAdapter
import com.smartover.bigburger.utility.TextUtilities
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_cart.rvProductList
import kotlinx.android.synthetic.main.fragment_product_list.llMainView
import kotlinx.android.synthetic.main.view_cart_row.*
import kotlinx.android.synthetic.main.view_no_connection.*


class CartFragment : BaseFragment(),CartPresenter.View {

    var cartPresenter: CartPresenterImpl? = null

    override fun updateThePage() {
        setUI()
    }

    companion object { val TAG: String = "CartFragment" }


    override fun getViewLayout(): Int {
        return R.layout.fragment_cart
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartPresenter = CartPresenterImpl(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    private fun setUI() {
        backTV.setOnClickListener{ MainActivity.instance?.removeFragment(TAG) }
        totalPrice.text = TextUtils.concat(TextUtilities.priceFormatting(cartPresenter!!.getTotal().toString(), ContextCompat.getColor(
            this.context!!, R.color.text_color))," ₺")
        rvProductList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CartAdapter(cartPresenter!!.getCart(),cartPresenter)
        }
    }

    override fun connectionError() {
        llNoConnection.visibility = View.VISIBLE
        productContainerCL.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Snackbar.make(llMainView, message, Snackbar.LENGTH_SHORT).show()
    }
}
