package com.example.rc3b3week

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.*
import com.example.rc3b3week.databinding.LongclickDialogBinding
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import java.nio.file.Files.size

private lateinit var binding:LongclickDialogBinding

class CustomDialog(mainActivity: MainActivity) : DialogFragment(){

    private var dialogListener: MyDialogListener? = null
    private var context: Context? = null

    fun CustomDialog(context: Context?) {
        this.context = context
    }

    fun setDialogListener(dialogListener: MyDialogListener?) {
        this.dialogListener = dialogListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = LongclickDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        binding.btDlDelete.setOnClickListener {
            dialogListener?.onPositiveClicked("삭제")
            dismiss()
        }

        return view
    }

}