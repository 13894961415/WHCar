package com.example.whcar.Home.Adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.whcar.Home.model.HomeVerGridModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class HomeVerGridAdapter extends BaseAdapter {
    private Context context;
    private List<HomeVerGridModel> models = new ArrayList<>();

    public HomeVerGridAdapter(Context context, List<HomeVerGridModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeVerGridModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_ver_grid_item,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.home_ver_grid_img);
            viewHolder.title = (TextView)convertView.findViewById(R.id.home_ver_grid_title);
            viewHolder.xinxi = (TextView)convertView.findViewById(R.id.home_ver_grid_xx);
            viewHolder.time = (TextView)convertView.findViewById(R.id.home_ver_grid_time);
            viewHolder.num = (TextView)convertView.findViewById(R.id.home_ver_grid_num);
            viewHolder.price = (TextView)convertView.findViewById(R.id.home_ver_grid_price);
            viewHolder.primeprice = (TextView)convertView.findViewById(R.id.home_ver_grid_primeprice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img.setImageResource(model.getImg());
        viewHolder.title.setText(model.getTitle());
        viewHolder.xinxi.setText(model.getXinxi());
        viewHolder.time.setText(model.getTime());
        viewHolder.num.setText(model.getNum());
        viewHolder.price.setText(model.getPrice());
        SpannableString spannableString = new SpannableString(model.getPrimeprice());
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span,0,spannableString.length()-1+1,SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        viewHolder.primeprice.setText(model.getPrimeprice());
        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView title,xinxi,time,num,price,primeprice;
    }
}