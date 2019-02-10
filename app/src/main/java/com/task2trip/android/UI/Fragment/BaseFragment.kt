package com.task2trip.android.UI.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.task2trip.android.Model.User.User
import com.task2trip.android.View.MainActivityView

abstract class BaseFragment : Fragment() {
    private var activityListener: MainActivityView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityView) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setResourceLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
    }

    protected fun navigateTo(resourceFragment: Int, args: Bundle?) {
        activityListener?.navigateTo(resourceFragment, args)
    }

    fun onMessage(message: String) {
        activityListener?.onMessage(message)
    }

    /**
     * Устанавливает видимость панели внизу
     */
    protected fun setBottomPanelVisibility(isVisible: Boolean) {
        //activityListener?.showBottomPanel(isVisible)
    }

    protected fun setUser(user: User) {
        activityListener?.setUser(user)
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
    abstract fun initComponents(@NonNull view: View)
}