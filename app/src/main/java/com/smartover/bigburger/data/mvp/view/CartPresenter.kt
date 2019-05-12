package com.smartover.bigburger.data.mvp.view

import com.smartover.bigburger.data.models.CartProduct
import com.smartover.bigburger.data.service.response.models.Product

interface CartPresenter {

    interface View : BaseView {
        fun updateThePage()
    }

    interface Presenter {
        fun getTotal():Double
        fun getCart():ArrayList<CartProduct>
        fun addToCart(product: Product)
        fun removeFromCart(product: Product)
    }

}