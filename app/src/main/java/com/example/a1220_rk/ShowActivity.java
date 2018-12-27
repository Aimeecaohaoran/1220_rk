package com.example.a1220_rk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.a1220_rk.view.GanFragment;
import com.example.a1220_rk.view.ShuFragment;
import com.example.a1220_rk.view.WoFragment;
import com.example.a1220_rk.view.YingFragment;
import com.example.a1220_rk.view.ZhuFragment;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragments;
    private Fragment fragment;
    private Fragment zhuFragment;
    private FragmentTransaction transaction;
    private GanFragment ganFragment;
    private YingFragment yingFragment;
    private ShuFragment shuFragment;
    private WoFragment woFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();

    }

    private void initView() {
        //初始化控件
        radioGroup=findViewById(R.id.radio_group);
        //fragment管理者
        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        //添加首页
        radioGroup.check(R.id.zhuye);
        zhuFragment = new ZhuFragment();
        fragments.add(zhuFragment);
        hideOthersFragment(zhuFragment,true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    // 首页
                    case R.id.zhuye:
                        hideOthersFragment(zhuFragment, false);
                        break;
                    //干货
                    case R.id.ganhuo:
                        if (ganFragment == null) {
                            ganFragment = new GanFragment();
                            fragments.add(ganFragment);
                            hideOthersFragment(ganFragment, true);
                        } else {
                            hideOthersFragment(ganFragment, false);
                        }
                        break;
                    // 影视
                    case R.id.yingshi:
                        if (yingFragment == null) {
                            yingFragment = new YingFragment();
                            fragments.add(yingFragment);
                            hideOthersFragment(yingFragment, true);
                        } else {
                            hideOthersFragment(yingFragment, false);
                        }
                        break;
                    // 书籍
                    case R.id.shuji:
                        if (shuFragment == null) {
                            shuFragment = new ShuFragment();
                            fragments.add(shuFragment);
                            hideOthersFragment(shuFragment, true);
                        } else {
                            hideOthersFragment(shuFragment, false);
                        }
                        break;
                    // 我的
                    case R.id.wode:
                        if (woFragment == null) {
                            woFragment = new WoFragment();
                            fragments.add(woFragment);
                            hideOthersFragment(woFragment, true);
                        } else {
                            hideOthersFragment(woFragment, false);
                        }
                        break;
                    default:
                        break;


                }
            }
        });
    }


    private void hideOthersFragment(Fragment showfragment, boolean add) {
        transaction = fragmentManager.beginTransaction();
        if (add)
            transaction.add(R.id.frag,showfragment);
        for (Fragment fragment:fragments) {
            if (showfragment.equals(fragment)){
                transaction.show(fragment);
            }else {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

}
