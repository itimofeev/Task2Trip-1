package com.task2trip.android.UI.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.task2trip.android.UI.CallBackView.HostActivityListener

abstract class BaseFragment : Fragment() {
    private var activityListener: HostActivityListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HostActivityListener) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(setResourceLayout(), container, false)
        initComponents(view)
        return view
    }

    fun navigateTo(resourceFragment: Int, args: Bundle?) {
//        activityListener?.navigateTo(resourceFragment, args)
    }

    fun onMessage(message: String) {
        //activityListener?.onMessage(message)
    }

    /**
     * Устанавливает видимость панели внизу
     */
    fun setBottomPanelVisibility(isVisible: Boolean) {
        //activityListener?.showBottomPanel(isVisible)
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }

    abstract fun getArgs(args: Bundle?)
    /**
     * Устанавливает разметку
     */
    abstract fun setResourceLayout(): Int

    /**
     * В это методе можно инициализировать свои компоненты
     */
    abstract fun initComponents(view: View)
}