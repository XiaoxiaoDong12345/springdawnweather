package com.example.xiaocomputer.springdawnweather.util;

import android.text.TextUtils;

import com.example.xiaocomputer.springdawnweather.db.City;
import com.example.xiaocomputer.springdawnweather.db.County;
import com.example.xiaocomputer.springdawnweather.db.Province;
import com.example.xiaocomputer.springdawnweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    public static boolean handleProvinceResponse(String reponse)
    {
        if (!TextUtils.isEmpty(reponse))
        {
            try {
                JSONArray allProvince=new JSONArray(reponse);
                for (int i=0;i<allProvince.length();i++)
                {
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();

                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCountyResponse(String reponse,int cityId)
    {
        if (!TextUtils.isEmpty(reponse))
        {
            try {
                JSONArray allCounties=new JSONArray(reponse);
                for (int i=0;i<allCounties.length();i++)
                {
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();



                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String reponse,int provinceId)
    {
        if (!TextUtils.isEmpty(reponse))
        {
            try {
                JSONArray allCities=new JSONArray(reponse);
                for (int i=0;i<allCities.length();i++)
                {
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();

                }
                return true;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static Weather handleWeatherResponse(String repsonse)
    {
        try {
            JSONObject jsonObject=new JSONObject(repsonse);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
