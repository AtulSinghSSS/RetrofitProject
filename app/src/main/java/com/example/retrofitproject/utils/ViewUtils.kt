package com.example.retrofitproject.utils
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


class ViewUtils {
    companion object {
        var customDialog: Dialog? = null


        fun ProgressBar.show() {
            visibility = View.VISIBLE
        }

        fun ProgressBar.hide() {
            visibility = View.GONE
        }

        fun toast(context: Context?, str: String?) {
            val toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        fun View.snackbar(message: String) {
            Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
                snackbar.setAction("Ok") {
                    snackbar.dismiss()
                }
            }.show()
        }

        fun hideProgressDialog() {
            if (customDialog != null) {
                customDialog!!.dismiss()
            }
        }

//        fun showProgressDialog(activity: Activity) {
//            try {
//                if (customDialog != null) {
//                    customDialog?.dismiss()
//                    customDialog = null
//                }
//                customDialog = Dialog(activity, R.style.CustomDialog)
//                val mView: View =
//                    LayoutInflater.from(activity).inflate(R.layout.progress_dialog, null)
//                customDialog!!.setContentView(mView)
//                customDialog!!.setCancelable(false)
//
//                if (!activity.isFinishing && !customDialog!!.isShowing) {
//                    customDialog!!.show()
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
    }
}

