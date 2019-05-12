package com.smartover.bigburger.data.models

import com.smartover.bigburger.data.service.response.models.Product


// The product which is customised for cart

class CartProduct  {

    var product:Product

    constructor(product: Product){
        this.product = product
        add()
    }

    var count: Int = 0
    var productTotal: Double = 0.0


    //Custom functions to calculate to product total price when product amount has been changed.
    fun add(){
        count ++
        productTotal = product.price * count
    }

    fun remove(){
        count --
        productTotal = product.price * count
    }

}