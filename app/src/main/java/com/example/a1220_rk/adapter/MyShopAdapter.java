package com.example.a1220_rk.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1220_rk.R;
import com.example.a1220_rk.bean.ShopBean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyShopAdapter extends BaseAdapter {
    private Context context;
    private ShopBean jsonBean;

    public MyShopAdapter(Context context, ShopBean jsonBean) {
        this.context = context;
        this.jsonBean = jsonBean;
    }

    @Override
    public int getCount() {
        return jsonBean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return jsonBean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyShopAdapter.ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView = View.inflate(context, R.layout.item_layout, null);
            holder.summary=convertView.findViewById(R.id.text1);
            holder.name=convertView.findViewById(R.id.text2);
            holder.imageUrl=convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else {
            holder = (MyShopAdapter.ViewHolder) convertView.getTag();
        }
        //赋值
        holder.summary.setText(jsonBean.getResult().get(position).getSummary());
        holder.name.setText(jsonBean.getResult().get(position).getName());
        ImageLoader.getInstance().displayImage(jsonBean.getResult().get(position).getImageUrl(),holder.imageUrl);
        return convertView;
    }
    class ViewHolder{
        TextView summary,name;
        ImageView imageUrl;
    }
}
