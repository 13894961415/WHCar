package com.example.whcar.Buy.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.whcar.Buy.Model.BuyHotListModel;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class BuyHotListAdapter extends BaseAdapter {
    private Context context;
    private List<BuyHotListModel> models = new ArrayList<>();
    private int mCurrentItem = 0;//选中项的下标
    private boolean isClick = false;//判断是否选中

    public BuyHotListAdapter(Context context, List<BuyHotListModel> models) {
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
        BuyHotListModel model = models.get(position);
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.buy_hot_lv_item,null);
            viewHolder.title = (TextView)convertView.findViewById(R.id.buy_hot_title);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.buy_hot_duihao);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(model.getTitle());
        viewHolder.img.setImageResource(model.getImg());


        //判断初始进来选中的下标==当前的下标 如果i=0时设置背景、文字颜色、以及左侧的竖直tab
        if (mCurrentItem == position){
            viewHolder.title.setTextColor(context.getResources().getColor(R.color.orange));
            viewHolder.img.setVisibility(View.VISIBLE);
        }else {
            viewHolder.title.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.img.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        TextView title;
        ImageView img;
    }

    public void setCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
    }

    public void setClick(boolean click) {
        this.isClick = click;
    }

}
