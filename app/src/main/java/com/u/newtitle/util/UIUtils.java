package com.u.newtitle.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by wuxiaolong on 2017/12/15.
 *
 * the UI Auxiliary class
 */

public class UIUtils {

    private static long lastClickTime = 0;
    private static final int MIN_CLICK_DELAY_TIME = 500;

    /**
     * get the screen width
     */
    public static int getScreenWidth(Context context) {

//        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
//        return manager.getDefaultDisplay().getWidth();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * @return get the screen height
     */
    public static int getScreenHeight(Context context) {
//        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
//        return manager.getDefaultDisplay().getHeight();

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * @param activity close the
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * dp to change the px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to change the dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * get the screen size
     * @param context
     * @return
     */
    public static Point getScreenSize(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * is to double click
     *
     * @return
     * @author 谭忠扬-YuriTam
     * @time 2017年2月21日
     */
    public static boolean isDoubleClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        } else {
            return true;
        }
    }

    /**
     * clear the last click event
     */
    public static void clearLastClickEvent() {
        lastClickTime = 0;
    }
}
