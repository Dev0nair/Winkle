package com.ismaelgr.winkle.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ismaelgr.winkle.R


abstract class BaseFragment(@LayoutRes idScreen: Int) : Fragment(idScreen), BaseContract.View {

    abstract override fun initElements()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initElements()
    }

    override fun hideLoading() {
        activity?.run {
            findViewById<View>(R.id.main_overlay)?.visibility = View.GONE
            findViewById<ContentLoadingProgressBar>(R.id.main_loading_progressbar)?.hide()
        }
    }

    override fun showLoading() {
        activity?.run {
            findViewById<View>(R.id.main_overlay)?.visibility = View.VISIBLE
            findViewById<ContentLoadingProgressBar>(R.id.main_loading_progressbar)?.show()
        }
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