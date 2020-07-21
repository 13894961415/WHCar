package com.example.whcar.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whcar.Home.model.HomeHorGridModel;

import com.example.whcar.Home.model.HomeHorGridModel2;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class HomeTwoGridAdapter  extends BaseAdapter {
    private Context context;
    private List<HomeHorGridModel2> models = new ArrayList<>();

    public HomeTwoGridAdapter(Context context, List<HomeHorGridModel2> models) {
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
        HomeHorGridModel2 model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_two_grid_item,null);
            viewHolder.txt = (TextView)convertView.findViewById(R.id.home_hor_re_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txt.setText(model.getTxt());
        return convertView;
    }

    class ViewHolder{
        TextView txt;
    }
}
