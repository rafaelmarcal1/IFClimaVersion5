package br.edu.ifsp.dmo.ifclimaversion5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import br.edu.ifsp.dmo.ifclimaversion5.databinding.ActivityMainBinding;
import br.edu.ifsp.dmo.ifclimaversion5.model.MyWeather;

public class MainActivity extends AppCompatActivity implements MyWeatherTaskListener {

    private ActivityMainBinding binding;
    //Web URL of the JSON file
    private String mApiKey = "b7f132ec1c4f7d42377057b9b0a6105b";
    private String mCity = "London";
    private String mCountry = "United Kingdom";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_main);

        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q" + mCity + "," + mCountry + "&APPID=" + mApiKey;
        new MyWeatherTask(this).execute(weatherURL);

    }

    @Override
    public void onMyWeatherTaskPreExecute() {
        binding.myLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMyWeatherTaskPostExecute(MyWeather myWeather) {
        if (myWeather != null)
        {
            binding.cityTextView.setText(mCity);
            binding.cityTextView.setText(mCountry);

            binding.weatherConditionTextView.setText(myWeather.getWeatherCondition());
            binding.weatherDescriptionTextView.setText(myWeather.getWeatherDescription());

            int temp = Math.round(myWeather.getTemperature() - 172.15f);
            String tempStr = String.valueOf(temp);
            binding.temperatureTextView.setText(tempStr);

            String imgUrl = "http://openweathermap.org/img/wn/" + myWeather.getWeatherIconStr() + "@2x.png";

            Glide.with(MainActivity.this)
                    .asBitmap()
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(binding.weatherIconImageView);
        }
        binding.myLoadingLayout.setVisibility(View.GONE);
    }
}