package com.smartover.bigburger.ui.adapter

import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smartover.bigburger.R
import com.smartover.bigburger.data.models.CartProduct
import com.smartover.bigburger.data.mvp.model.CartPresenterImpl
import com.smartover.bigburger.ui.activity.MainActivity
import com.smartover.bigburger.utility.TextUtilities

class CartAdapter(
    private val products: ArrayList<CartProduct>,
    private val cartPresenterImpl: CartPresenterImpl?
) : RecyclerView.Adapter<CartAdapter.CartItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartItemHolder(inflater, parent)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        val product = products[position]
        holder.bind(product, cartPresenterImpl)
    }


    class CartItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.view_cart_row, parent, false)) {

        private var image: ImageView? = null
        private var title: TextView? = null
        private var description: TextView? = null
        private var price: TextView? = null
        private var increaseTV: TextView? = null
        private var decreaseTV: TextView? = null
        private var amountTV: TextView? = null
        private var container: ConstraintLayout? = null
        private var isExpanded: Boolean = false


        init {
            image = itemView.findViewById(R.id.productImageIV)
            title = itemView.findViewById(R.id.titleTV)
            price = itemView.findViewById(R.id.priceTV)
            increaseTV = itemView.findViewById(R.id.increaseTV)
            decreaseTV = itemView.findViewById(R.id.decreaseTV)
            amountTV = itemView.findViewById(R.id.amountTV)
            description = itemView.findViewById(R.id.descriptionTV)
            container = itemView.findViewById(R.id.productContainerCL)
        }

        fun bind(
            product: CartProduct,
            cartPresenterImpl: CartPresenterImpl?
        ) {

            Glide.with(MainActivity.instance!!)
                .load(product.product.thumbnail)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.burger_large)
                )
                .into(image!!)

            title?.text = product.product.title
            description?.text = product.product.description
            amountTV?.text = product.count.toString()

            price?.text = TextUtils.concat(TextUtilities.priceFormatting(product.productTotal.toString(), ContextCompat.getColor(MainActivity.instance!!, R.color.colorPrimaryDark)), " ₺")

            container?.setOnClickListener { expandOrCollapseTheRow() }

            increaseTV?.setOnClickListener { cartPresenterImpl?.addToCart(product.product) }
            decreaseTV?.setOnClickListener { cartPresenterImpl?.removeFromCart(product.product) }

        }

        private fun expandOrCollapseTheRow() {

            if (isExpanded) {
                description?.visibility = GONE
                isExpanded = false
            } else {
                description?.visibility = VISIBLE
                isExpanded = true
            }

        }

    }
}