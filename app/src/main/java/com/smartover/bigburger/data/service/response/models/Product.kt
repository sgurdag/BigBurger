package com.smartover.bigburger.data.service.response.models

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import com.google.gson.annotations.SerializedName
import com.smartover.bigburger.R
import com.smartover.bigburger.utility.TextUtilities

class Product {

    @SerializedName("ref")
    val ref: String = ""
    @SerializedName("title")
    val title: String = ""
    @SerializedName("description")
    val description: String = ""
    @SerializedName("thumbnail")
    val thumbnail: String = ""
    @SerializedName("price")
    val price: Double = 0.0

    // Price format customisation in UI
    fun getFormattedPrice(context: Context): SpannableString {
        return TextUtilities.priceFormatting(price.toString(), ContextCompat.getColor(context, R.color.colorPrimaryDark))
    }

}