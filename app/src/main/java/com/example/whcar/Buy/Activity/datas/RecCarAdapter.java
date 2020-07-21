package com.example.whcar.Buy.Activity.datas;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;



public class RecCarAdapter extends RecyclerView.Adapter<RecCarAdapter.Holder> {

    private List<CarBean.CarInfo> mList;

    private Activity mActivity;

    public RecCarAdapter(Activity activity) {
        mList = new ArrayList<>();
        mActivity = activity;
    }

    public void addDatas(List<CarBean.CarInfo> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_car, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        CarBean.CarInfo car = mList.get(position);

        holder.tv_name.setText(car.getName());
        Glide.with(mActivity).load(car.getLogoUrl()).into(holder.iv_logo);
        //Glide.with(mActivity).load("http://image.bitautoimg.com/bt/car/default/images/logo/masterbrand/png/100/m_92_100.png").into(holder.iv_logo);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv)
        TextView tv_name;

        @BindView(R.id.item_iv)
        ImageView iv_logo;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_content)
        void tvClick() {
            Toast.makeText(itemView.getContext(), "你点击到了" + tv_name.getText(), Toast.LENGTH_SHORT).show();
        }
    }




}
