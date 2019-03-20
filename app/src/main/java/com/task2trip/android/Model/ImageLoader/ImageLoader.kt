package com.task2trip.android.Model.ImageLoader

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.task2trip.android.R
import java.io.File

val DefaultCropType = ImageCropType.CROP_ROUNDED

class ImageLoader() {

    constructor(url: String, imageView: ImageView, cropType: ImageCropType = DefaultCropType) : this() {
        if (url.isNotEmpty()) {
            loadImage(url, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, cropType)
        }
    }

    constructor(uri: Uri?, imageView: ImageView, cropType: ImageCropType = DefaultCropType): this() {
        uri?.let {
            loadImage(it, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, cropType)
        }
    }

    constructor(file: File?, imageView: ImageView, cropType: ImageCropType = DefaultCropType): this() {
        file?.let {
            loadImage(it, imageView, R.drawable.vector_ic_default_travel, R.drawable.vector_ic_default_travel, cropType)
        }
    }

    private fun loadImage(file: File,
                          imageView: ImageView,
                          placeHolderRes: Int,
                          errorRes: Int,
                          cropType: ImageCropType = DefaultCropType) {
        loadImageCreator(Picasso.get().load(file), imageView, placeHolderRes, errorRes, cropType)
    }

    private fun loadImage(uri: Uri,
                          imageView: ImageView,
                          placeHolderRes: Int,
                          errorRes: Int,
                          cropType: ImageCropType = DefaultCropType) {
        loadImageCreator(Picasso.get().load(uri), imageView, placeHolderRes, errorRes, cropType)
    }

    private fun loadImage(url: String,
                          imageView: ImageView,
                          placeHolderRes: Int,
                          errorRes: Int,
                          cropType: ImageCropType = DefaultCropType) {
        if (url.isNotEmpty()) {
            loadImageCreator(Picasso.get().load(url), imageView, placeHolderRes, errorRes, cropType)
        }
    }

    private fun loadImageCreator(creator: RequestCreator,
                                 imageView: ImageView,
                                 placeHolderRes: Int,
                                 errorRes: Int,
                                 cropType: ImageCropType = DefaultCropType) {
        with(creator) {
            placeholder(placeHolderRes)
            error(errorRes)
            //networkPolicy(NetworkPolicy.OFFLINE)
            //memoryPolicy(MemoryPolicy.NO_STORE)
            when(cropType) {
                ImageCropType.CROP_CIRCLE -> {
                    transform(com.task2trip.android.Model.ImageLoader.ImageTransform.CIRCLE)
                }
                ImageCropType.CROP_ROUNDED -> {
                    transform(com.task2trip.android.Model.ImageLoader.ImageTransform.ROUNDED)
                }
                ImageCropType.CROP_SQUARE -> {
                    //TODO
                }
                ImageCropType.CROP_NO -> {
                    //no transformation
                }
            }
            into(imageView)
        }
    }
}