package com.github.yangkl.x.android.widgets.dialog.core

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.yangkl.x.android.R
import com.github.yangkl.x.android.utils.DisplayUtils
import com.github.yangkl.x.android.widgets.dialog.ViewHolder

/**
 * XDialog类
 */
abstract class XBaseDialog : DialogFragment() {

    //左右边距
    private var margin: Int = 0

    //宽度
    private var width: Int = 0

    //高度
    private var height: Int = 0

    //灰度深浅
    private var dimAmount: Float = 0.5F

    //显示的位置
    private var gravity = Gravity.CENTER

    //是否点击外部取消
    private var outCancel = true

    // dialog主题
    @StyleRes
    var dialogTheme: Int = R.style.XDialogStyle

    // 动画
    @StyleRes
    var animStyle = 0

    // 布局资源ID
    @LayoutRes
    var dialogLayoutId = 0

    /**
     * 初始化布局资源ID
     */
    abstract fun initLayoutId(): Int

    /**
     * 转换视图
     */
    abstract fun convertView(holder: ViewHolder, dialogX: XBaseDialog)

    /**
     * 初始化对话框主题
     */
    open fun initDialogTheme(): Int = dialogTheme

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置主题
        setStyle(STYLE_NO_TITLE, initDialogTheme())
        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN)
            width = savedInstanceState.getInt(WIDTH)
            height = savedInstanceState.getInt(HEIGHT)
            dimAmount = savedInstanceState.getFloat(DIM)
            gravity = savedInstanceState.getInt(GRAVITY)
            outCancel = savedInstanceState.getBoolean(CANCEL)
            dialogTheme = savedInstanceState.getInt(THEME)
            animStyle = savedInstanceState.getInt(ANIM)
            dialogLayoutId = savedInstanceState.getInt(LAYOUT)
        }
    }

    @Nullable
    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        dialogLayoutId = initLayoutId()
        val view: View = inflater.inflate(dialogLayoutId, container, false)
        convertView(ViewHolder.create(view), this)
        return view
    }

    override fun onStart() {
        super.onStart()
        initParams()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MARGIN, margin)
        outState.putInt(WIDTH, width)
        outState.putInt(HEIGHT, height)
        outState.putFloat(DIM, dimAmount)
        outState.putInt(GRAVITY, gravity)
        outState.putBoolean(CANCEL, outCancel)
        outState.putInt(THEME, dialogTheme)
        outState.putInt(ANIM, animStyle)
        outState.putInt(LAYOUT, dialogLayoutId)
    }

    /**
     * 初始化参数
     */
    private fun initParams() {
        val window = dialog!!.window
        if (window != null) {
            val lp = window.attributes
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount
            if (gravity != 0) {
                lp.gravity = gravity
            }
            when (gravity) {
                Gravity.LEFT, Gravity.LEFT or Gravity.BOTTOM, Gravity.LEFT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.LeftAnimation
                }
                Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.TopAnimation
                }
                Gravity.RIGHT, Gravity.RIGHT or Gravity.BOTTOM, Gravity.RIGHT or Gravity.TOP -> if (animStyle == 0) {
                    animStyle = R.style.RightAnimation
                }
                Gravity.BOTTOM -> if (animStyle == 0) {
                    animStyle = R.style.BottomAnimation
                }
            }

            //设置dialog宽度
            when (width) {
                0 -> lp.width = DisplayUtils.getScreenWidth(requireContext()) - 2 * DisplayUtils.dp2px(requireContext(), margin)
                -1 -> lp.width = WindowManager.LayoutParams.WRAP_CONTENT
                else -> lp.width = DisplayUtils.dp2px(requireContext(), width)
            }

            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.height = DisplayUtils.dp2px(requireContext(), height)
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle)
            window.attributes = lp
        }
        isCancelable = outCancel
    }

    open fun setMargin(margin: Int): XBaseDialog {
        this.margin = margin
        return this
    }

    open fun setWidth(width: Int): XBaseDialog {
        this.width = width
        return this
    }

    open fun setHeight(height: Int): XBaseDialog {
        this.height = height
        return this
    }

    open fun setDimAmount(dimAmount: Float): XBaseDialog {
        this.dimAmount = dimAmount
        return this
    }

    open fun setGravity(gravity: Int): XBaseDialog {
        this.gravity = gravity
        return this
    }

    open fun setOutCancel(outCancel: Boolean): XBaseDialog {
        this.outCancel = outCancel
        return this
    }

    open fun setAnimStyle(@StyleRes animStyle: Int): XBaseDialog {
        this.animStyle = animStyle
        return this
    }

    open fun show(manager: FragmentManager): XBaseDialog {
        val ft: FragmentTransaction = manager.beginTransaction()
        if (this.isAdded) {
            ft.remove(this).commit()
        }
        ft.add(this, System.currentTimeMillis().toString())
        ft.commitAllowingStateLoss()
        return this
    }

    ///////////////////////////////////////////////////////////////////////////
    ///  伴生对象 ///
    ///////////////////////////////////////////////////////////////////////////
    companion object {
        private const val MARGIN = "margin"
        private const val WIDTH = "width"
        private const val HEIGHT = "height"
        private const val DIM = "dim_amount"
        private const val GRAVITY = "gravity"
        private const val CANCEL = "out_cancel"
        private const val THEME = "theme"
        private const val ANIM = "anim_style"
        private const val LAYOUT = "layout_id"
    }
}