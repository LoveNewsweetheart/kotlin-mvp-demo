package com.xinhuan.wms.demo.provider.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xinhuan.wms.demo.provider.R;


/**
 * Created by guo on 2019/2/13.
 */

public class CommonDialog extends Dialog implements View.OnClickListener {

    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvLeft;
    private TextView mTvRight;
    private View mBtnDivider;
    private DialogClickListener mClickListener;

    public CommonDialog(@NonNull Context context) {
        super(context, R.style.AlertDialogStyle);
        init();
    }

    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected CommonDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        setContentView(R.layout.layout_common_dialog);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams attributes = window.getAttributes();
        Point point = getScreenSize(window);
        attributes.width = (int) (point.x * 0.9);
//        window.setBackgroundDrawable(null);
        mTvTitle = findViewById(R.id.tvTitle);
        mTvContent = findViewById(R.id.tvContent);
        mTvLeft = findViewById(R.id.tvLeft);
        mTvRight = findViewById(R.id.tvRight);
        mBtnDivider = findViewById(R.id.btnDivider);
        mTvRight.setOnClickListener(this);
        mTvLeft.setOnClickListener(this);
    }

    @Override
    public void show() {
        if(getContext() instanceof Activity){
            Activity activity = (Activity)getContext();
            if (activity.isFinishing()) {
                return;
            }
        }
        String leftStr = mTvLeft.getText().toString();
        String rightStr = mTvRight.getText().toString();
        if (TextUtils.isEmpty(leftStr)) {
            mTvLeft.setVisibility(View.GONE);
            mBtnDivider.setVisibility(View.GONE);
            mTvRight.setBackgroundResource(R.drawable.shape_bottom_corner_bg);
        }
        if (TextUtils.isEmpty(rightStr)) {
            mTvRight.setVisibility(View.GONE);
            mBtnDivider.setVisibility(View.GONE);
            mTvLeft.setBackgroundResource(R.drawable.shape_bottom_corner_bg);
        }
        String title = mTvTitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            //Content内容向上 Ui更美观
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mTvContent.getLayoutParams();
            params.topMargin = params.topMargin - 30;
            mTvContent.setLayoutParams(params);
        }
        super.show();
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setTilte(int resTitleId) {
        mTvTitle.setText(resTitleId);
    }

    public void setContent(String content) {
        mTvContent.setText(content);
    }

    public void setContent(int resContentId) {
        mTvContent.setText(resContentId);
    }

    public void setLeftText(String leftContent) {
        mTvLeft.setText(leftContent);
    }

    public void setLeftText(int resLeftContentId) {
        mTvLeft.setText(resLeftContentId);
    }

    public void setRightText(String rightContent) {
        mTvRight.setText(rightContent);
    }

    public void setRightText(int resRightContentId) {
        mTvRight.setText(resRightContentId);
    }

    public TextView getTvContent() {
        return mTvContent;
    }

    public void setOnDialogClickListener(DialogClickListener listener) {
        this.mClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvLeft) {
            if (mClickListener != null) {
                mClickListener.onLeftClick();
            }
            dismiss();
        } else if (id == R.id.tvRight) {
            if (mClickListener != null) {
                mClickListener.onRightClick();
            }
            dismiss();
        }
    }

    public interface DialogClickListener {
        void onLeftClick();

        void onRightClick();
    }

    public static class DefaultDialogClickListener implements DialogClickListener {

        @Override
        public void onLeftClick() {

        }

        @Override
        public void onRightClick() {

        }
    }

    private Point getScreenSize(Window window) {
        Display defaultDisplay = window.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }
}
