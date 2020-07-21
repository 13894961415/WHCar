package com.example.whcar.refresh_load;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by jiang on 16/8/18.
 */

public interface BaseRefreshListener {


    /**
     * 刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();

    void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
