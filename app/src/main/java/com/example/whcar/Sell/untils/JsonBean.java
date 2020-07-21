package com.example.whcar.Sell.untils;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * TODO<json数据源>
 *
 * @author: 小嵩
 * @date: 2017/3/16 15:36
 */

public class JsonBean implements IPickerViewData {


    /**
     * name : 省份
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区"]}]
     */
    private String city_id;
    private String city_name;
    private List<CityBean> area;


    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public List<CityBean> getArea() {
        return area;
    }

    public void setArea(List<CityBean> area) {
        this.area = area;
    }

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.city_name;
    }



    public static class CityBean {
        /**
         * area_name : 城市
         * business : ["东城区","西城区","崇文区","昌平区"]
         */

        private String area_id;
        private String city_id;
        private String area_name;
        private List<BusinessBean> business;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public List<BusinessBean> getBusiness() {
            return business;
        }

        public void setBusiness(List<BusinessBean> business) {
            this.business = business;
        }
    }

    public static class BusinessBean {
        /**
         * name : 城市
         * area : ["东城区","西城区","崇文区","昌平区"]
         */

        private String business_id;
        private String business_name;
        private String area_id;

        public String getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(String business_id) {
            this.business_id = business_id;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }
    }
}
