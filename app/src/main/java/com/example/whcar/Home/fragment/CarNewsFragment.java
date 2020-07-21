package com.example.whcar.Home.fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whcar.Home.Adapter.CarNewsAdapter;
import com.example.whcar.Home.model.CarNewsModel;
import com.example.whcar.Home.utils.MyGridView;
import com.example.whcar.R;

import java.util.ArrayList;
import java.util.List;

public class CarNewsFragment extends Fragment {

    private View view;

    private MyGridView gv;
    private List<CarNewsModel> models = new ArrayList<>();
    private CarNewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_carnews,container,false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        for (int i=0;i<10;i++){
            CarNewsModel model1 = new CarNewsModel(R.drawable.aq,"可变悬架");
            models.add(model1);
        }

    }

    private void initView() {
        gv = (MyGridView)view.findViewById(R.id.carnews_gv);
        adapter = new CarNewsAdapter(getContext(),models);
        gv.setAdapter(adapter);
    }


}
