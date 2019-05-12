package com.smartover.bigburger.data.models

import com.smartover.bigburger.data.service.response.models.Product


/**
 * This singleton class is a kind of helper to manage the session of user and the data of the cart.
 */
class User {

    private var cart = ArrayList<CartProduct>()

    companion object {
        private val instance: User = User()

        fun getSession(): User {
            return instance
        }

    }

    fun getCart():ArrayList<CartProduct>{
        return cart
    }

    fun addToCart(product: Product) {

        //If the cart already includes the product, then increase the count of the product
        for (i in 0 until cart.size) {

            val baseProduct: CartProduct = cart.get(i)

            if (product.ref.equals(baseProduct.product.ref)) {
                cart.get(i).add()
                return
            }
        }
        // if the cart doesn't include this product, then add a new product.
        cart.add(CartProduct(product))
    }

    fun removeFromCart(product: Product) {

        //Detect the product, then decrease the count of the product
        //if the count is already 1, then remove the product from the cart.
        for (i in 0 until cart.size) {

            val baseProduct: CartProduct = cart.get(i)

            if (product.ref.equals(baseProduct.product.ref)) {
                if (cart.get(i).count == 1) {
                    cart.removeAt(i)
                } else {
                    cart.get(i).remove()
                }
                return
            }
        }
    }

    fun getCartTotal():Double{

        var total = 0.0
        for (i in 0 until cart.size){
            total += cart.get(i).productTotal
        }
        return total
    }
}