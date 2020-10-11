package com.ismaelgr.winkle.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment(@LayoutRes idScreen: Int) : Fragment(idScreen), BaseContract.View {

    abstract override fun initElements()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
    }

    open fun hideKeyboard() {
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).run {
            hideSoftInputFromWindow(requireView().windowToken, 0)
        }
    }

    override fun showError(error: String) {
        Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
    }

    override fun getMyString(@StringRes stringId: Int): String {
        return getString(stringId)
    }
}