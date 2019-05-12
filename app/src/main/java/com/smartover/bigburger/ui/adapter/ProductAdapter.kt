package com.smartover.bigburger.ui.adapter

import android.support.constraint.ConstraintLayout
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
import com.smartover.bigburger.data.mvp.model.ProductListPresenterImpl
import com.smartover.bigburger.data.service.response.models.Product
import com.smartover.bigburger.ui.activity.MainActivity

class ProductAdapter(
    private val products: ArrayList<Product>,
    private val productListPresenter: ProductListPresenterImpl?
) : RecyclerView.Adapter<ProductAdapter.ProductListItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductListItemHolder(inflater, parent)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductListItemHolder, position: Int) {
        val product = products[position]
        holder.bind(product, productListPresenter)
    }


    class ProductListItemHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.view_product_list_row, parent, false)) {

        private var image: ImageView? = null
        private var title: TextView? = null
        private var description: TextView? = null
        private var price: TextView? = null
        private var addToCart: TextView? = null
        private var container: ConstraintLayout? = null
        private var isExpanded: Boolean = false


        init {
            image = itemView.findViewById(R.id.productImageIV)
            title = itemView.findViewById(R.id.titleTV)
            price = itemView.findViewById(R.id.priceTV)
            addToCart = itemView.findViewById(R.id.addToCartTV)
            description = itemView.findViewById(R.id.descriptionTV)
            container = itemView.findViewById(R.id.productContainer)
        }

        fun bind(product: Product, productListPresenter: ProductListPresenterImpl?) {

            Glide.with(MainActivity.instance!!)
                .load(product.thumbnail)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.burger_large)
                )
                .into(image!!)

            title?.text = product.title
            description?.text = product.description
            price?.text = TextUtils.concat(product.getFormattedPrice(MainActivity.instance!!), " ₺")

            container?.setOnClickListener { expandOrCollapseTheRow() }

            addToCart?.setOnClickListener {
                productListPresenter?.addToCart(product)
            }

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