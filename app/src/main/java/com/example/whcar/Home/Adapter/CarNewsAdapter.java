package com.example.whcar.Home.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
;

import com.example.whcar.Home.model.CarNewsModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class CarNewsAdapter extends BaseAdapter {
    private Context context;
    private List<CarNewsModel> models = new ArrayList<>();

    public CarNewsAdapter(Context context, List<CarNewsModel> models) {
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
        CarNewsModel model = models.get(position);
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.carnews_gv_item,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.carnews_gv_img);
            viewHolder.text = (TextView)convertView.findViewById(R.id.carnews_gv_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.img.setImageResource(model.getImg());
        viewHolder.text.setText(model.getTitle());
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView text;
    }
}
