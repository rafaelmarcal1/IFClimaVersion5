package br.edu.ifsp.dmo.ifclimaversion5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.edu.ifsp.dmo.ifclimaversion5.model.MyWeather;

public class MyJSONParser {
    public static MyWeather getMyWeather(String jsonStr)
    {
        MyWeather myWeather = new MyWeather();

        try
        {
            JSONObject rootJsonObject = new JSONObject(jsonStr);

            //Get Weather condition
            JSONArray weatherJsonArray = rootJsonObject.getJSONArray("weather");
            JSONObject jsonObject1 = weatherJsonArray.getJSONObject(0);
            myWeather.setWeatherCondition(jsonObject1.getString("main"));
            myWeather.setWeatherDescription(jsonObject1.getString("description"));
            myWeather.setWeatherIconStr(jsonObject1.getString("icon"));

            //Get temperature
            JSONObject jsonObject2 = rootJsonObject.getJSONObject("main");
            myWeather.setTemperature((float) jsonObject2.getDouble("temp"));

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return myWeather;
    }
}
