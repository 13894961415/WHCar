package com.example.whcar.Home.fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whcar.Home.utils.MyScrollView;
import com.example.whcar.Home.utils.PublicStaticClass;
import com.example.whcar.R;


@SuppressLint("ValidFragment")
public class GoodsDetailWebFragment extends Fragment {

    public WebView wv_detail;
    private View rootView;
    private WebSettings webSettings;
    private LayoutInflater inflater;
    private String htmlstring;

    @SuppressLint("ValidFragment")
    public GoodsDetailWebFragment(String htmlstr){
        this.htmlstring = htmlstr;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        rootView = inflater.inflate(R.layout.fragment_item_detail_web,container,false);

        initView();
        initWebView(rootView);
        return rootView;
    }

    private void initWebView(View rootView) {
        wv_detail = (WebView)rootView.findViewById(R.id.wv_detail);
        wv_detail.setFocusable(false);

        wv_detail.loadData(htmlstring,"text/html; charset=UTF-8",null);
        webSettings = wv_detail.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setBlockNetworkImage(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_detail.setWebViewClient(new GoodsDetailWebViewClient());
    }


    private void initView() {
        MyScrollView oneScrollView = (MyScrollView)rootView.findViewById(R.id.oneScrollview);
        oneScrollView.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY==0){
                    PublicStaticClass.IsTop = true;
                }else {
                    PublicStaticClass.IsTop = false;
                }
            }

            @Override
            public void notBottom() {

            }
        });
    }


    private class GoodsDetailWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webSettings.setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }
}
