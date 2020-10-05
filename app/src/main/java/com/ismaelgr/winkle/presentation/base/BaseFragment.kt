package com.ismaelgr.winkle.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment(@LayoutRes idScreen: Int) : Fragment(idScreen), BaseContract.View {

    abstract override fun initElements()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
    }
}