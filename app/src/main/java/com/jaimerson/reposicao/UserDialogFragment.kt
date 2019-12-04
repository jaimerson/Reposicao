package com.jaimerson.reposicao

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class UserDialogFragment(val callback: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle("Are you sure?")
                .setPositiveButton("YES", DialogInterface.OnClickListener { _dialog, _id ->
                    callback()
                })
                .setNegativeButton("NO", DialogInterface.OnClickListener { _dialog, _id ->
                    dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}