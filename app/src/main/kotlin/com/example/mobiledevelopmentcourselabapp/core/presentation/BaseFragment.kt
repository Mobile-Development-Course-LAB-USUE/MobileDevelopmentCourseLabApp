package com.example.mobiledevelopmentcourselabapp.core.presentation

import android.app.Dialog
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mobiledevelopmentcourselabapp.R
import moxy.MvpAppCompatFragment

open class BaseFragment: MvpAppCompatFragment(), BaseMvpView {

    private var loadingDialog: Dialog? = null

    override fun setLoadingVisibility(isVisible: Boolean) {
        if (isVisible) {
            loadingDialog =
                AlertDialog.Builder(requireActivity())
                    .setMessage(getString(R.string.loading))
                    .setCancelable(false)
                    .create()
            loadingDialog?.show()
        } else {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    override fun showError(throwable: Throwable) {
        context?.let { Toast.makeText(it, throwable.localizedMessage, Toast.LENGTH_LONG).show() }
    }

}