package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

/**
 * Created by whjin on 16-11-11.
 */
public class Utility {
    //return province data from http, and sava data to db
    public synchronized static boolean handleProvinceResponse(CoolWeatherDB db, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvince = response.split(",");
            if (null!=allProvince && allProvince.length>0) {
                for (String p : allProvince) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    db.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //return city data from http, and save data to db
    public synchronized static boolean handleCityResponse(CoolWeatherDB db, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCity = response.split(",");
            if (null!=allCity && allCity.length>0) {
                for (String c : allCity) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    db.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //return county data from http, and save data to db
    public synchronized static boolean handleCountyResponse(CoolWeatherDB db, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounty = response.split(",");
            if (null!=allCounty && allCounty.length>0) {
                for (String cc : allCounty) {
                    String[] array = cc.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    db.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}