package com.example.whcar.Sell;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whcar.R;
import com.example.whcar.Sell.Activity.GuJiaActivity;
import com.example.whcar.Sell.Activity.YuActivity;

public class SellFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button gu;
    private Button yu;

    private Button zi;
    String ss= "13159511437";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_sell,container,false);
        inivtView();
        return view;
    }

    private void inivtView() {
        gu  = (Button)view.findViewById(R.id.gu);
        gu.setOnClickListener(this);

        yu  = (Button)view.findViewById(R.id.yu);
        yu.setOnClickListener(this);

        zi = (Button)view.findViewById(R.id.zi);
        zi.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gu:
                startActivity(new Intent(getContext(), GuJiaActivity.class));
                break;
            case R.id.yu:
                startActivity(new Intent(getContext(), YuActivity.class));
                break;
            case  R.id.zi:
                Intent intent = new Intent();
                //设置拨打电话的动作
                intent.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intent.setData(Uri.parse("tel:" + ss));
                //开启打电话的意图
                startActivity(intent);
                break;

        }


    }

}