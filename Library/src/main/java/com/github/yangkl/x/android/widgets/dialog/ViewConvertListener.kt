package com.github.yangkl.x.android.widgets.dialog

import android.os.Parcel
import android.os.Parcelable
import com.github.yangkl.x.android.widgets.dialog.core.XBaseDialog

abstract class ViewConvertListener() : Parcelable {

    abstract fun convertView(holder: ViewHolder, dialogX: XBaseDialog)

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewConvertListener> {
        override fun createFromParcel(parcel: Parcel): ViewConvertListener {
            return object : ViewConvertListener(parcel) {
                override fun convertView(holder: ViewHolder, dialogX: XBaseDialog) {
                }
            }
        }

        override fun newArray(size: Int): Array<ViewConvertListener?> {
            return arrayOfNulls(size)
        }
    }
}