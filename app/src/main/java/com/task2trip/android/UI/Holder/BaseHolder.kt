package com.task2trip.android.UI.Holder

import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<IL>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setData(@NonNull item: IL)
    abstract fun setItemClickListener(@Nullable listener: View.OnClickListener?)
}