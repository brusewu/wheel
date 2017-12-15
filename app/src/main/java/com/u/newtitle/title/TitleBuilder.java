package com.u.newtitle.title;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.u.newtitle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wuxiaolong on 2017/12/14.
 */

public class TitleBuilder {

    @BindView(R.id.title_iv_left)
    ImageView titleIvLeft;
    @BindView(R.id.title_tv_left)
    TextView titleTvLeft;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.title_iv)
    ImageView titleIv;
    @BindView(R.id.title_iv_right)
    ImageView titleIvRight;
    @BindView(R.id.title_tv_right)
    TextView titleTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;


    public TitleBuilder(Activity activity) {
        ButterKnife.bind(this, activity);
    }

    public TitleBuilder(View view) {
        ButterKnife.bind(this, view);
    }

    public void invisible() {
        llTitle.setVisibility(View.INVISIBLE);
    }
    public void gone() {
        llTitle.setVisibility(View.GONE);
    }
    /**
     * set the title image picture of the inner
     *
     */
    public TitleBuilder setInnerTitleBgRes(int resId) {
        rlTitle.setBackgroundResource(resId);
        return this;
    }

    /***
     * set the title image picture of the external
     *
     */
    public TitleBuilder setExternalTitleBgRes(int resId) {
        llTitle.setBackgroundResource(resId);
        return this;
    }

    /**
     * set the title background color of inner
     * @param color
     * @return
     */
    public TitleBuilder setInnerTitleBgColor(int color) {
        rlTitle.setBackgroundColor(color);
        return this;
    }


    /***
     * set the title background color of the external
     * @param color
     * @return
     */
    public TitleBuilder setExternalTitleBgColor(int color) {
        llTitle.setBackgroundColor(color);
        return this;
    }

    /**
     * set the title text
     * @param text
     * @return
     */
    public TitleBuilder setTitleText(String text) {
        titleTv.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        titleTv.setText(text);
        return this;
    }

    /**
     * set the title image
     * @param resId
     * @return
     */
    public TitleBuilder setTitleImage(int resId) {
        titleIv.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titleIv.setImageResource(resId);
        return this;
    }


    /***
     * set the left title text image and onclick
     * @param resId
     * @return
     */
    public TitleBuilder setLeftImage(int resId) {
        titleIvLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titleIvLeft.setImageResource(resId);
        return this;
    }

    public TitleBuilder setLeftText(String text) {
        titleTvLeft.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        titleTvLeft.setText(text);
        return this;
    }

    public TitleBuilder setLeftOnClickListener(View.OnClickListener listener) {
        if (titleIvLeft.getVisibility() == View.VISIBLE) {
            titleIvLeft.setOnClickListener(listener);
        } else if (titleTvLeft.getVisibility() == View.VISIBLE) {
            titleTvLeft.setOnClickListener(listener);
        }
        return this;
    }

    /***
     * set the right title image text and onclick
     *
     */
    public TitleBuilder setRightImage(int resId) {
        titleIvRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        titleIvRight.setImageResource(resId);
        return this;
    }

    public TitleBuilder setRightText(String text) {
        titleTvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE
                : View.VISIBLE);
        titleTvRight.setText(text);
        return this;
    }

    public TitleBuilder setRightOnClickListener(View.OnClickListener listener) {
        if (titleIvRight.getVisibility() == View.VISIBLE) {
            titleIvRight.setOnClickListener(listener);
        } else if (titleTvRight.getVisibility() == View.VISIBLE) {
            titleTvRight.setOnClickListener(listener);
        }
        return this;
    }

    public View build(){
        return rlTitle;
    }

}
