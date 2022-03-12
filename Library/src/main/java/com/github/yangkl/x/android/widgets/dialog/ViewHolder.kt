package com.github.yangkl.x.android.widgets.dialog

import android.util.SparseArray
import android.view.View
import android.widget.TextView

class ViewHolder(private val convertView: View) {

    private var views: SparseArray<View> = SparseArray()

    /**
     * 根据ID获得View
     */
    fun <T : View> getView(viewId: Int): T? {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById<T>(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    /**
     * 获得ConvertView对象
     */
    fun getConvertView(): View {
        return convertView
    }

    /**
     * 设置TextView文案
     */
    fun setText(viewId: Int, text: String?) {
        val textView = getView<TextView>(viewId)
        textView?.text = text
    }

    /**
     * 设置TextView文案
     */
    fun setText(viewId: Int, textId: Int) {
        val textView = getView<TextView>(viewId)
        textView?.setText(textId)
    }

    /**
     * 设置TextView颜色
     */
    fun setTextColor(viewId: Int, colorId: Int) {
        val textView = getView<TextView>(viewId)
        textView?.setTextColor(colorId)
    }

    ///////////////////////////////////////////////////////////////////////////
    ///  伴生对象 ///
    ///////////////////////////////////////////////////////////////////////////

    companion object {
        /**
         * 创建ViewHolder
         */
        fun create(view: View): ViewHolder {
            return ViewHolder(view)
        }
    }
}