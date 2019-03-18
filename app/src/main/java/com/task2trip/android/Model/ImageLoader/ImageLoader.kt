package com.task2trip.android.Model.ImageLoader

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.task2trip.android.Network.ApiMethods
import com.task2trip.android.R

class ImageLoader() {
    constructor(url: String, imageView: ImageView) : this() {
        loadImage(url, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, false)
    }

    fun loadImage(url: String, imageView: ImageView) {
        loadImage(url, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, false)
    }

    private fun loadImage(url: String, imageView: ImageView, placeHolderRes: Int, errorRes: Int, isCircle: Boolean) {
        with (Picasso.get().load(ApiMethods.HTTP_ADDRESS + url)) {
            placeholder(placeHolderRes)
            error(errorRes)
            if (isCircle) {
                transform(ImageTransformCircle())
            }
            into(imageView)
        }
    }

    fun loadImageCircle(url: String, imageView: ImageView) {
        loadImage(url, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, true)
    }
}