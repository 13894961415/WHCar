package com.example.whcar.Home.model;

/**
 * Created by wt on 2018/3/19.
 */

public class SearchModel {
    private String searchText;

    public SearchModel(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
