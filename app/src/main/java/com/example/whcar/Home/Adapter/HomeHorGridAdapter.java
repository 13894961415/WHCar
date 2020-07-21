package com.example.whcar.Home.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.whcar.Home.model.HomeHorGridModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class HomeHorGridAdapter extends BaseAdapter {
    private Context context;
    private List<HomeHorGridModel> models = new ArrayList<>();

    public HomeHorGridAdapter(Context context, List<HomeHorGridModel> models) {
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
        HomeHorGridModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_hor_grid_item,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.home_hor_grid_img);
            viewHolder.txt = (TextView)convertView.findViewById(R.id.home_hor_grid_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img.setImageResource(model.getImg());
        viewHolder.txt.setText(model.getTxt());
        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView txt;
    }
}
