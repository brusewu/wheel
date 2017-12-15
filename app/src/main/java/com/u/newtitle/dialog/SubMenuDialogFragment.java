package com.u.newtitle.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.u.newtitle.R;
import com.u.newtitle.util.SubMenuInfo;
import com.u.newtitle.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wuxiaolong on 2017/12/15.
 */

public class SubMenuDialogFragment extends DialogFragment {


    @BindView(R.id.gv_dialog_menu)
    GridView gvDialogMenu;
    Unbinder unbinder;

    private SubMenuAdapter mMenuAdapter;
    private List<SubMenuInfo> mSubMenuList;
    private OnDialogListener mDialogListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置样式
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ic_dialog_style);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.dialog_sub_menu, container);
        unbinder = ButterKnife.bind(this, contentView);
        getDialog().getWindow().getDecorView().setPadding(0, 0, 0, 0); //消除边距
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(params);
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setWindowAnimations(R.style.DialogWindowAnim);
        //初始化适配器
        if (mMenuAdapter == null) {
            mMenuAdapter = new SubMenuAdapter(getContext());
            gvDialogMenu.setAdapter(mMenuAdapter);
        }
        //设置回调下标
        gvDialogMenu.setOnItemClickListener((parent, view, position, id) -> {
            dismiss();
            if (mDialogListener != null) {
                mDialogListener.onItemClick(position);
            }
        });
    }

    @OnClick(R.id.iv_cancel)
    public void onClicked(View view) {
        if (UIUtils.isDoubleClick()) return;
        switch (view.getId()){
            case R.id.iv_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setSubMenuList(List<SubMenuInfo> subMenuList) {
        this.mSubMenuList = subMenuList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setDialogListener(OnDialogListener dialogListener) {
        this.mDialogListener = dialogListener;
    }

    public interface OnDialogListener {
        void onItemClick(int position);
    }

    /**
     * 子菜单适配器
     */
    class SubMenuAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private Context mContext;

        public SubMenuAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            if (mSubMenuList != null)
                return mSubMenuList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mSubMenuList != null) return mSubMenuList.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_sub_menu, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            SubMenuInfo menuInfo = mSubMenuList.get(position);
            holder.ivLogoIcon.setImageResource(menuInfo.getImgId());
            holder.tvDesc.setText(mContext.getString(menuInfo.getDescId()));

            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.iv_logo_icon)
            ImageView ivLogoIcon;
            @BindView(R.id.tv_desc)
            TextView tvDesc;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

    }
}
