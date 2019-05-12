package com.smartover.bigburger.data.mvp.model

import com.smartover.bigburger.data.models.User
import com.smartover.bigburger.data.mvp.view.ProductListViewPresenter
import com.smartover.bigburger.data.service.BigBurgerAPI
import com.smartover.bigburger.data.service.response.models.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductListPresenterImpl(mainView: ProductListViewPresenter.View?) : ProductListViewPresenter.Presenter {


    /**
     * To prevent mess in the data traffic, I manage the Cart processes only through presenter level.
     */
    override fun addToCart(product: Product) {
        User.getSession().addToCart(product)
        view!!.gotoCart()
    }

    var view: ProductListViewPresenter.View? = mainView

    @NonNull
    var disposable: Disposable? = null


    /**
     * fetching the data from URL
     */
    override fun loadData() {

        view!!.showProgresss()
        if (view!!.checkInternet()) {

            // response of the requests might be improved by encapsulating the response by a base response class to handle the errors easily
            // I didn't prefer it because  we hit only one API in the whole app. Also, because of the lack of time :(
            disposable = BigBurgerAPI.instance.getProducts()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { listResponse ->
                        view!!.dismissProgresss()
                        val responseCode = listResponse.code()
                        when (responseCode) {
                            200, 201, 202 -> {
                                view!!.onProductListReceived(listResponse.body())
                            }
                            401 -> {
                            }
                            402 -> {
                            }
                            500 -> {
                            }
                            501 -> {
                            }

                        }
                    }, { error ->
                        view!!.dismissProgresss()
                        view!!.onError(error)
                    }
                )
        } else {
            view!!.dismissProgresss()
            view!!.connectionError()
        }
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}