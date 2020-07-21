package com.example.whcar.Buy.Adapter;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.whcar.Buy.Model.BuyListModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyListAdapter extends BaseAdapter {

    private Context context;
    private List<BuyListModel> models = new ArrayList<>();

    public BuyListAdapter(Context context, List<BuyListModel> models) {
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
        BuyListModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_listview,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.buy_lv_img);
            viewHolder.name = (TextView)convertView.findViewById(R.id.buy_id_name);
            viewHolder.xinxi = (TextView)convertView.findViewById(R.id.buy_lv_xx);
            viewHolder.time = (TextView)convertView.findViewById(R.id.buy_lv_time);
            viewHolder.km = (TextView)convertView.findViewById(R.id.buy_lv_km);
            viewHolder.area = (TextView)convertView.findViewById(R.id.buy_lv_area);
            viewHolder.price = (TextView)convertView.findViewById(R.id.buy_id_price);
            viewHolder.newprice = (TextView)convertView.findViewById(R.id.buy_lv_newprice);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img.setImageResource(model.getImg());
        viewHolder.name.setText(model.getName());
        viewHolder.xinxi.setText(model.getXinxi());
        viewHolder.time.setText(model.getTime());
        viewHolder.km.setText(model.getKm());
        viewHolder.area.setText(model.getArea());
        viewHolder.price.setText(model.getPrice());
        SpannableString spannableString = new SpannableString(model.getNewprice());
        StrikethroughSpan span = new StrikethroughSpan();
        spannableString.setSpan(span,0,spannableString.length()-1+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        if (viewHolder.newprice.length()>0){
            viewHolder.newprice.setText(model.getNewprice());
        }
        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView name,xinxi,time,km,area,price,newprice;
    }
}
