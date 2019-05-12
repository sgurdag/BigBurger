package com.smartover.bigburger.utility

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.smartover.bigburger.R
import java.text.DecimalFormat

object TextUtilities {

    fun priceFormatting(input: String, textColor:Int): SpannableString {

        try {

            val inputDouble = java.lang.Double.parseDouble(input)
            val formatter = DecimalFormat("###,###,##0.00")
            val value = formatter.format(inputDouble).toString()

            val ss1 = SpannableString(value)
            ss1.setSpan(
                ForegroundColorSpan(textColor),
                0,
                value.length - 2,
                0
            )
            ss1.setSpan(
                ForegroundColorSpan(textColor),
                value.length - 2,
                value.length,
                0
            )
            ss1.setSpan(
                TopAlignSuperscriptSpan(1.0.toFloat()),
                value.length - 2,
                value.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            return ss1

        } catch (e: Exception) {

            Log.e("Price Formatting", "Price Formatting Exception  >>>> : $input")
            return SpannableString(input + " ₺")
        }

    }
}
