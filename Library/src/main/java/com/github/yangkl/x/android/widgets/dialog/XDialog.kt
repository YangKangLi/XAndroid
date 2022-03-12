package com.github.yangkl.x.android.widgets.dialog

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.github.yangkl.x.android.widgets.dialog.core.XBaseDialog

class XDialog : XBaseDialog() {

    private var convertListener: ViewConvertListener? = null

    override fun initLayoutId(): Int = dialogLayoutId

    fun setDialogLayout(@LayoutRes dialogLayoutId: Int): XDialog {
        this.dialogLayoutId = dialogLayoutId
        return this
    }

    fun setViewConvertListener(convertListener: ViewConvertListener?): XDialog {
        this.convertListener = convertListener
        return this
    }

    override fun convertView(holder: ViewHolder, dialogX: XBaseDialog) {
        convertListener?.convertView(holder, dialogX)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("listener", convertListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        convertListener = null
    }

    companion object {
        /**
         * 初始化XDialog
         */
        fun init(): XDialog {
            return XDialog()
        }
    }
}