package com.u.newtitle;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;


import com.u.newtitle.dialog.EditDialogFragment;
import com.u.newtitle.dialog.ProgressDialogFragment;
import com.u.newtitle.dialog.SubMenuDialogFragment;
import com.u.newtitle.dialog.TextDialogFragment;
import com.u.newtitle.title.TitleBuilder;
import com.u.newtitle.util.SubMenuInfo;
import com.u.newtitle.util.UIUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public final static String FORCE = "force";
    private boolean mForceLogin;
    private ProgressDialogFragment mDialogFragment;
    private EditDialogFragment editDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            mForceLogin = args.getBoolean(FORCE, false);
        }
        initTitle();
    }

    //初始化标题栏
    private void initTitle() {
        new TitleBuilder(this)
                .setExternalTitleBgRes(R.drawable.bg_blue_icon)
                .setTitleText("See You")
                .setLeftImage(mForceLogin ? 0 : R.drawable.arrow_icon)
                .build();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_dialog:
                testTextDialog();
                break;

            case R.id.progress:
                showProgress();
                break;

            case R.id.edit:
                testEditDialog();
                break;

            case R.id.sub:
                testSub();
                break;
            default:
                break;
        }
    }

    //test the text dialog
    public void testTextDialog(){
        TextDialogFragment dialogFragment = TextDialogFragment.newInstance();
        dialogFragment.setDialogType(TextDialogFragment.DIALOG_TYPE_WARNING);
        dialogFragment.setTitleText("test");
        dialogFragment.setContentText("see", Gravity.CENTER);
        dialogFragment.setNegativeText("no");
        dialogFragment.setPositionText("yes");
        dialogFragment.setDialogListener(new TextDialogFragment.OnDialogListener() {


            @Override
            public void onCancel() {

            }

            @Override
            public void onConfirm() {
//                finish();
            }
        });
        dialogFragment.show(getSupportFragmentManager(), null);
    }


    public void showProgress() {
        if (mDialogFragment == null) {
            mDialogFragment = new ProgressDialogFragment();
        }
        mDialogFragment.setTipTitle("haha");
        mDialogFragment.setTip("waiting");
        mDialogFragment.setCancelable(false);

        mDialogFragment.show(getFragmentManager(), null);
    }

    public void testEditDialog(){

        editDialog = new EditDialogFragment();
        editDialog.setDialogType(EditDialogFragment.DIALOG_TYPE_WARNING);
        editDialog.addTitle("password");
        editDialog.addEditText1(InputType.TYPE_CLASS_NUMBER, 8, null, true);
        editDialog.setPositionText("nen");
        editDialog.setNegativeText("ene");
        editDialog.setDialogListener(new EditDialogFragment.OnDialogListener() {
            @Override
            public void onCancel() {
                editDialog.dismiss();
            }

            @Override
            public void onConfirm(HashSet<String> inputData) {

                editDialog.dismiss();

            }
        });
        editDialog.show(getSupportFragmentManager(), null);
    }

    //
    public void testSub(){
        List<SubMenuInfo> menuList = new ArrayList<>();
        menuList.add(new SubMenuInfo(R.drawable.sign_out_icon, R.string.one));
        menuList.add(new SubMenuInfo(R.drawable.trans_settle_icon, R.string.two));
        menuList.add(new SubMenuInfo(R.drawable.operator_icon, R.string.three));
        menuList.add(new SubMenuInfo(R.drawable.lock_ter_icon, R.string.four));
        menuList.add(new SubMenuInfo(R.drawable.version_icon, R.string.one));
        SubMenuDialogFragment dialogFragment = new SubMenuDialogFragment();
        dialogFragment.setSubMenuList(menuList);
        dialogFragment.setDialogListener(position -> {
            if (UIUtils.isDoubleClick()) return;
            switch (position) {
                case 0://

                    break;
                case 1://

                    break;
                case 2://

                    break;
                case 3://

                    break;
                case 4://

                    break;
                default:
                    break;
            }
        });
        dialogFragment.show(getSupportFragmentManager(), null);
    }
}
