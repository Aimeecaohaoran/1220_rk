package com.example.a1220_rk.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.a1220_rk.R;
import com.example.a1220_rk.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZhuFragment extends Fragment {
    private String[] titles;
    private List<TextView> list;
    private HorizontalScrollView hs;
    private LinearLayout linearlayout;
    private ViewPager viewpager;
    private View mView;
    private TextView tooltv;
    private View view;
    private String m="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=10&page=1";

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.zhu_layout, container, false);
        //初始胡控件
        hs=view.findViewById(R.id.hor);
        linearlayout=view.findViewById(R.id.lin);
        viewpager=view.findViewById(R.id.viewpager);

        //创建数组，存放标题
        titles = new String[] { "正在上映", "即将上映" };
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager(), titles);
        viewpager.setAdapter(myPagerAdapter);
        //创建集合,存放textview
        list = new ArrayList<TextView>();
        for (int i = 0; i < titles.length; i++) {
            //创建textview
            TextView textView = new TextView(getActivity());
            textView.setText(titles[i]);
            textView.setTextSize(20);
            textView.setTextColor(Color.rgb(250,250,250));
            textView.setId(i + 20);
            //点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    viewpager.setCurrentItem(id-20);
                }
            });
            //刚进程序第一个默认红色,其他为黑色
            if(i ==0){
                textView.setTextColor(Color.RED);
            }else {
                textView.setTextColor(Color.BLACK);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20,40,20,40);
            //添加到布局中
            linearlayout.setBackground(getActivity().getDrawable(R.color.bai));
            linearlayout.addView(textView,layoutParams);
            //添加到集合
            list.add(textView);
        }
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                //list中存的是 textview
                for (int i = 0; i < list.size(); i++) {
                    if(position==i){
                        list.get(i).setTextColor(getResources().getColor(R.color.hong));
                    }else{
                        list.get(i).setTextColor(getResources().getColor(R.color.hei));
                    }
                }
                //获取当前的textview
                TextView textView = list.get(position);
                //width是每次滑动的距离
                int width = textView.getWidth()+10;
                //让scrollView滑动   滑动距离是textview之间的间距
                hs.scrollTo(width*position,0);

            }
            //选中
            @Override
            public void onPageSelected(int i) {

            }
            //改变
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;
    }
}
