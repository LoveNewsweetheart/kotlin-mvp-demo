package com.xinhuan.wms.demo.provider.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * 高度与宽度一致的布局
 */
class HeightWidthEqFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)
    }

}
