package com.smartover.bigburger.data.mvp.view

import com.smartover.bigburger.data.service.response.models.Product

interface ProductListViewPresenter {

    interface View : BaseView {
        fun gotoCart()
        fun onProductListReceived(products: ArrayList<Product>?)
        fun onError(throwable: Throwable)
    }

    interface Presenter {
        fun addToCart(product: Product)
        fun loadData()
        fun onStop()
    }
}
