package com.example.whcar.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whcar.Home.model.HomeHorGridModel1;
import com.example.whcar.Home.model.HomeHorGridModel3;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class HomeThreeGridAdapter extends BaseAdapter {
    private Context context;
    private List<HomeHorGridModel3> models = new ArrayList<>();

    public HomeThreeGridAdapter(Context context, List<HomeHorGridModel3> models) {
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
        HomeHorGridModel3 model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_re_grid_item1,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.home_hor_re_img);
            viewHolder.txt = (TextView)convertView.findViewById(R.id.home_hor_re_txt);
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
