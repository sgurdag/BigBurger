package com.smartover.bigburger.data.service

import com.smartover.bigburger.data.service.response.models.Product
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * This interface is where the whole endpoints are stored.
 * We will reach these endpoints through BigBurgerAPI
 */
interface API_BB {

    @GET("dump/mobiletest1.json")
    fun getProducts(): Observable<Response<ArrayList<Product>>>

}