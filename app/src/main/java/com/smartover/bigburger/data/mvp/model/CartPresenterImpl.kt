package com.smartover.bigburger.data.mvp.model

import com.smartover.bigburger.data.models.CartProduct
import com.smartover.bigburger.data.models.User
import com.smartover.bigburger.data.mvp.view.CartPresenter
import com.smartover.bigburger.data.service.response.models.Product

class CartPresenterImpl(mainView: CartPresenter.View?) : CartPresenter.Presenter {

    override fun getCart(): ArrayList<CartProduct> {
        return User.getSession().getCart()
    }

    override fun getTotal(): Double {
        return User.getSession().getCartTotal()
    }


    var view: CartPresenter.View? = mainView

    override fun addToCart(product: Product) {
        User.getSession().addToCart(product)
        view!!.updateThePage()
    }

    override fun removeFromCart(product: Product) {
        User.getSession().removeFromCart(product)
        view!!.updateThePage()
    }

}