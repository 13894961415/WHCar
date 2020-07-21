package com.example.whcar.Buy.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.whcar.Buy.Model.BuyPriceModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class ButPriceAdapter extends BaseAdapter {
    private Context context;
    private List<BuyPriceModel> models = new ArrayList<>();
    private int item = 0;
    private boolean isClick = false;

    public ButPriceAdapter(Context context, List<BuyPriceModel> models) {
        this.context = context;
        this.models = models;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
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
        BuyPriceModel model = models.get(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_price_grid,null);
            viewHolder.buy_price_text = (TextView)convertView.findViewById(R.id.buy_price_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.buy_price_text.setText(model.getText());
        if (item == position){
            viewHolder.buy_price_text.setTextColor(context.getResources().getColor(R.color.orange));
            viewHolder.buy_price_text.setBackgroundResource(R.drawable.orange_kuang_back);
        }else {
            viewHolder.buy_price_text.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.buy_price_text.setBackgroundResource(R.drawable.huitoumingback);
        }
        return convertView;
    }

    class ViewHolder{
        TextView buy_price_text;
    }
}
