package com.example.whcar.Buy.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.whcar.Buy.Model.BuyMoreTypeModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyMoreTypeAdapter extends BaseAdapter {

    private Context context;
    private List<BuyMoreTypeModel> models = new ArrayList<>();
    private int mcurrentItem = 0;
    private boolean onClick = false;

    public int getMcurrentItem() {
        return mcurrentItem;
    }

    public void setMcurrentItem(int mcurrentItem) {
        this.mcurrentItem = mcurrentItem;
    }

    public boolean isOnClick() {
        return onClick;
    }

    public void setOnClick(boolean onClick) {
        this.onClick = onClick;
    }

    public BuyMoreTypeAdapter(Context context, List<BuyMoreTypeModel> models) {
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
        BuyMoreTypeModel model = models.get(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_more_gv_item,null);
            viewHolder.text = (TextView)convertView.findViewById(R.id.buy_more_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.text.setText(model.getText());
        if (mcurrentItem == position){
            viewHolder.text.setTextColor(context.getResources().getColor(R.color.orange));
            viewHolder.text.setBackgroundResource(R.drawable.orange_kuang_back);
        }else {
            viewHolder.text.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.text.setBackgroundResource(R.drawable.huitoumingback);
        }
        return convertView;
    }

    class ViewHolder{
        TextView text;
    }
}
