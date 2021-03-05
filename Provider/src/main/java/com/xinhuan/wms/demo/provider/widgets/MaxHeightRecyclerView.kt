package com.xinhuan.wms.demo.provider.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.xinhuan.wms.demo.provider.R

/**
 * 带有最大高度属性的RecyclerView
 */
class MaxHeightRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var mMaxHeight = 0

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightRecyclerView);
        mMaxHeight = arr.getLayoutDimension(R.styleable.MaxHeightRecyclerView_maxHeight, mMaxHeight);
        arr.recycle()
    }


    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        if (mMaxHeight > 0) {
            val newHeightSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST)
            super.onMeasure(widthSpec, newHeightSpec)
        } else {
            super.onMeasure(widthSpec, heightSpec)
        }
    }


}