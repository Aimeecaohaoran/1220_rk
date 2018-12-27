package com.example.a1220_rk.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1220_rk.R;
import com.example.a1220_rk.adapter.MyShopAdapter;
import com.example.a1220_rk.bean.ShopBean;
import com.example.a1220_rk.utils.MyHttp;
import com.example.a1220_rk.xlistview.XListView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment implements XListView.IXListViewListener {
    private static MainFragment fragment1;
    private View view;
    private XListView listView;
    private String mnr;
    private MyShopAdapter shopAdapter;
    int page=1;
    int pageSize=10;

    public static Fragment getinstance(String title){
       fragment1 = new MainFragment();
        Bundle bundle = new Bundle();
        //添加值
        bundle.putString("title",title);
        fragment1.setArguments(bundle);
        return fragment1;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取值
        Bundle arguments = getArguments();
        String title = arguments.getString("title");
        //创建textview
        TextView textView = new TextView(getActivity());
        textView.setText("1608C"+title);
        //获取view绑定的数据
        if (view==null){
            view=inflater.inflate(R.layout.mainfr_layout,container,false);
            listView = view.findViewById(R.id.mf_xlv);
            listView.setXListViewListener(this);
            //加载刷新
            listView.setPullLoadEnable(true);
            listView.setPullRefreshEnable(true);
            //获取数据
            switch (title){
                case "正在上映":
                    mnr="tt";
                    getData();
                    break;
                case "即将上映":
                    mnr="shehui";
                    getData();
                    break;

            }
            listView.setAdapter(shopAdapter);

        }
        return view;
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        page=1;
        new Runnable(){
            @Override
            public void run() {
                getData();
                shopAdapter.notifyDataSetChanged();
            }
        }.run();
        onLoad();
    }
    //上拉加载
    @Override
    public void onLoadMore() {
        page++;
        new Runnable(){
            @Override
            public void run() {
                getData();
                shopAdapter.notifyDataSetChanged();
            }
        }.run();
        onLoad();
    }
    //关闭刷新，下拉
    private void onLoad(){
        listView.stopRefresh();
        listView.stopLoadMore();
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        listView.setRefreshTime(formatter.format(curDate));
    }

    private void getData() {
        new MyAsn().execute();

    }
    class MyAsn extends AsyncTask<Integer,Integer,String>{
        public   String mPath="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=10&page=1";
        //http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=10&page?"+page+"&pageSize="+pageSize+""


        @Override
        protected String doInBackground(Integer... integers) {
            return MyHttp.getHttpCon(getActivity(),mPath,"GET");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null){
                Gson gson = new Gson();
                ShopBean shopBean = gson.fromJson(s, ShopBean.class);
                if (shopBean!=null){
                     shopAdapter = new MyShopAdapter(getActivity(), shopBean);
                    listView.setAdapter(shopAdapter);
                }
            }
        }
    }
}
