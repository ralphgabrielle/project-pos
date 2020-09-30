package com.ralph.gabb.projectpos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

abstract class BaseDialogFragment: DialogFragment(), ConstructView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutId?.let {
            return LayoutInflater.from(context).inflate(it, container, false)
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewCreated()
    }

    fun show(supportFragmentManager: FragmentManager) {
        show(supportFragmentManager, dialogTag())
    }

    private fun dialogTag() = javaClass.name

}