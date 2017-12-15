package com.u.newtitle.util;

/**
 * Created by wuxiaolong on 2017/12/15.
 *  Submenu entity class
 */

public class SubMenuInfo {

    private int imgId;
    private int descId;

    public SubMenuInfo(int imgId, int descId) {
        this.imgId = imgId;
        this.descId = descId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

}
