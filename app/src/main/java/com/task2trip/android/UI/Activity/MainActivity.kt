package com.task2trip.android.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task2trip.android.Presenter.MainActivityPresenter
import com.task2trip.android.R
import com.task2trip.android.View.MainActivityView

class MainActivity : AppCompatActivity(), MainActivityView {
    private val presenter: MainActivityPresenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onError(message: String) {
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
