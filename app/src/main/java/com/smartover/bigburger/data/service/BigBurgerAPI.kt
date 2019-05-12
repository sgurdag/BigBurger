package com.smartover.bigburger.data.service

import com.google.gson.GsonBuilder
import com.smartover.bigburger.BuildConfig
import com.smartover.bigburger.data.service.response.models.Product
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BigBurgerAPI {

    private val api_bb: API_BB

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api_bb = retrofit.create(API_BB::class.java)
    }

    companion object {
        private var apiClient: BigBurgerAPI? = null

        val instance: BigBurgerAPI
            get() {
                if (apiClient == null) {
                    apiClient = BigBurgerAPI()
                }
                return apiClient as BigBurgerAPI
            }
    }


    fun getProducts(): Observable<Response<ArrayList<Product>>> {
        return api_bb.getProducts()
    }
}