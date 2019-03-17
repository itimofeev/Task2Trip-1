package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.PhotoSourceAdapter
import com.task2trip.android.UI.Listener.ItemClickListener

class SelectPhotoSourceDialog: DialogFragment(), ItemClickListener<String> {
    private var lastSelectedValue = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (activity != null) {
            val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_search_category, null)
            initRecycleView(view)

            return AlertDialog.Builder(activity!!)
                .setTitle("Выберите пункт и нажмите кнопку OK")
                .setPositiveButton("ok") { dialog, _ ->
                    setSelectedResult()
                    dialog.dismiss()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, Intent())
                    dialog.dismiss() }
                .setView(view)
                .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    private fun setSelectedResult() {
        val intent = Intent()
        intent.putExtra(Constants.EXTRA_DIALOG_PHOTO_SOURCE, lastSelectedValue)
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }

    private fun initRecycleView(view: View) {
        val rvSearchCategory: RecyclerView = view.findViewById<RecyclerView>(R.id.rvSearchCategory)
        val photoSource = ArrayList<String>()
        photoSource.add(Constants.EXTRA_PHOTO_SOURCE_CAMERA)
        photoSource.add(Constants.EXTRA_PHOTO_SOURCE_STORAGE)
        val adapter = PhotoSourceAdapter(photoSource)
        adapter.setClickListener(this)
        rvSearchCategory.layoutManager = LinearLayoutManager(view.context)
        rvSearchCategory.adapter = adapter
    }

    override fun onItemClick(item: String, position: Int) {
        lastSelectedValue = item
    }
}


fun SelectPhotoSourceDialog.show(targetFragment: Fragment?) {
    this.setTargetFragment(targetFragment, Constants.REQUEST_DIALOG_CAMERA_SOURCE)
    val manager = targetFragment?.fragmentManager
    manager?.let {
        this.show(it, this.javaClass::class.java.name)
    }
}