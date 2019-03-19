package com.task2trip.android.Model.ImageLoader

import android.graphics.*
import com.squareup.picasso.Transformation
import android.graphics.RectF

class ImageTransformRounded(val radius: Float = 8f): Transformation {

    override fun transform(source: Bitmap?): Bitmap {
        source?.let {
            val size = Math.min(it.width, it.height)

            val x = (it.width - size) / 2
            val y = (it.height - size) / 2

            val squaredBitmap = Bitmap.createBitmap(it, x, y, size, size)
            if (squaredBitmap != it) {
                it.recycle()
            }

            val bitmap = Bitmap.createBitmap(size, size, it.config)

            val canvas = Canvas(bitmap)
            val paint = Paint()
            paint.shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.isAntiAlias = true

            val r = size / radius
            canvas.drawRoundRect(RectF(0f, 0f, it.width * 1f, it.height * 1f), r, r, paint)

            squaredBitmap.recycle()
            return bitmap
        }
        return Bitmap.createBitmap(50, 50, Bitmap.Config.ALPHA_8)
    }

    override fun key(): String {
        return "rounded"
    }
}