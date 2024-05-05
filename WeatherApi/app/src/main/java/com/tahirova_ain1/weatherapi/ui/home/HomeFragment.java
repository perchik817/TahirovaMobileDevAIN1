package com.tahirova_ain1.weatherapi.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieDrawable;
import com.tahirova_ain1.weatherapi.R;
import com.tahirova_ain1.weatherapi.databinding.FragmentHomeBinding;
import com.tahirova_ain1.weatherapi.models.Clouds;
import com.tahirova_ain1.weatherapi.models.Main;
import com.tahirova_ain1.weatherapi.models.Model;
import com.tahirova_ain1.weatherapi.models.Sys;
import com.tahirova_ain1.weatherapi.models.Wind;
import com.tahirova_ain1.weatherapi.remotedata.RetrofitBuilder;
import com.tahirova_ain1.weatherapi.remotedata.WeatherApi;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    Integer temperature;
    Integer temperatureMax;
    Integer temperatureMin;
    String currentTime = java.text.DateFormat.getDateTimeInstance().format(new Date());
    int humidityC;
    final String apiKey = WeatherApi.URL;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rainLotty.setAnimation(R.raw.rain);
        binding.rainLotty.setRepeatCount(LottieDrawable.INFINITE);
        binding.rainLotty.playAnimation();

        binding.sunLotty.setAnimation(R.raw.sun);
        binding.sunLotty.setRepeatCount(LottieDrawable.INFINITE);
        binding.sunLotty.playAnimation();

        binding.snowLotty.setAnimation(R.raw.snow1);
        binding.snowLotty.setRepeatCount(LottieDrawable.INFINITE);
        binding.snowLotty.playAnimation();

        binding.localTime.setText(currentTime);

        Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather("Bishkek", apiKey);
        call.enqueue(new Callback<Model>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                assert response.body() != null;
                Main mainModel = response.body().getMain();
                    Wind windModel = response.body().getWind();
                    Clouds cloudsModel = response.body().getClouds();
                    Sys sysModel = response.body().getSys();

                    Double temp = mainModel.getTemp();
                    Double tempMax = mainModel.getTempMax();
                    Double tempMin = mainModel.getTempMin();

                    temperature = makeFormFaringate(temp);
                    temperatureMax = makeFormFaringate(tempMax);
                    temperatureMin = makeFormFaringate(tempMin);

                    binding.tempC.setText(temperature + " °C");
                    if(temperature<=15){
                        binding.sunLotty.setVisibility(View.INVISIBLE);
                        setNoHotWeather();
                    }
                    binding.tempMaxMin.setText(String.format("%s °C↑\n%s °C↓", temperatureMax, temperatureMin));
                    if (binding.cityName.getText().toString().equals("Name of City"))
                        binding.cityName.setText("Bishkek");
                    else
                        binding.cityName.setText(binding.inputCity.getText());
                    binding.humidity.setText(mainModel.getHumidity() + " %");
                    if(humidityC >= 60){
                        isMayRain();
                    }
                    binding.pressure.setText(mainModel.getPressure() + "\nmBar");
                    binding.wind.setText(windModel.getSpeed() + " m/s");
                    binding.cloud.setText(cloudsModel.getAll() + " %");
                    binding.sunrise.setText(getCurrentDateTime(sysModel.getSunrise()));
                    binding.sunset.setText(getCurrentDateTime(sysModel.getSunset()));
                    binding.timeZone.setText(String.valueOf(response.body().getTimezone()));
                    if(response.body().getTimezone() <= 6500 && response.body().getTimezone() >= -27500){
                        setNight();
                    }else {
                        setDay();
                    }

                setCondition();
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable throwable) {
                Toast.makeText(requireActivity(), "No Weather data" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.slideUpBottomSheet.setOnClickListener(v -> {
            if(binding.bottomSheet.getVisibility() == View.GONE) binding.bottomSheet.setVisibility(View.VISIBLE);
            else binding.bottomSheet.setVisibility(View.GONE);

            binding.blueSky.setVisibility(View.VISIBLE);
            binding.sunLotty.setVisibility(View.VISIBLE);
            binding.badWeatherSky.setVisibility(View.INVISIBLE);
            binding.inputCity.setText("");
            binding.condition.setText("...");
            binding.rainLotty.setVisibility(View.INVISIBLE);
        });
        binding.search.setOnClickListener(v1 -> {
            if(!binding.inputCity.getText().toString().isEmpty()){
                Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather(binding.inputCity.getText().toString(), apiKey);
                call.enqueue(new Callback<Model>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                        assert response.body() != null;
                        Main mainModel = response.body().getMain();
                        Wind windModel = response.body().getWind();
                        Clouds cloudsModel = response.body().getClouds();
                        Sys sysModel = response.body().getSys();

                        Double temp = mainModel.getTemp();
                        Double tempMax = mainModel.getTempMax();
                        Double tempMin = mainModel.getTempMin();

                        temperature = makeFormFaringate(temp);
                        temperatureMax = makeFormFaringate(tempMax);
                        temperatureMin = makeFormFaringate(tempMin);

                        binding.tempC.setText(temperature + " °C");
                        if(temperature<=15){
                            binding.sunLotty.setVisibility(View.INVISIBLE);
                            setNoHotWeather();
                        }
                        binding.tempMaxMin.setText(String.format("%s °C↑\n%s °C↓", temperatureMax, temperatureMin));
                        binding.cityName.setText(binding.inputCity.getText());
                        binding.humidity.setText(mainModel.getHumidity() + " %");
                        if(humidityC >= 60){
                            isMayRain();
                        }
                        binding.pressure.setText(mainModel.getPressure() + "\nmBar");
                        binding.wind.setText(String.format("%s m/s", windModel.getSpeed()));
                        binding.cloud.setText(cloudsModel.getAll() + " %");
                        binding.sunrise.setText(getCurrentDateTime(sysModel.getSunrise()));
                        binding.sunset.setText(getCurrentDateTime(sysModel.getSunset()));
                        binding.timeZone.setText(String.valueOf(response.body().getTimezone()));
                        if(response.body().getTimezone() <= 6500 && response.body().getTimezone() >= -27500){
                            setNight();
                        }else {
                            setDay();
                        }

                        setCondition();
                    }
                    @Override
                    public void onFailure(@NonNull Call<Model> call, @NonNull Throwable throwable) {
                        Toast.makeText(requireActivity(), "Input city's name" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                binding.bottomSheet.setVisibility(View.GONE);
            } else {
                Toast.makeText(requireActivity(), "Enter the city's name.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCurrentDateTime(long dt) {
        return java.text.DateFormat.getDateTimeInstance().format(new Date(dt));
    }

    @SuppressLint("SetTextI18n")
    public void setCondition() {
        if (temperature >= 25 && temperature <= 50) {
            binding.blueSky.setVisibility(View.VISIBLE);
            binding.condition.setText("boiling");
            dryWeather();
            // Hide rain animation and "it may rain" status
            binding.rainLotty.setVisibility(View.INVISIBLE);
            binding.isRaining.setText("");
        } else if (temperature < 25 && temperature > 13) {
            binding.blueSky.setVisibility(View.VISIBLE);
            binding.condition.setText("light \nsunny");
            dryWeather();
            // Hide rain animation and "it may rain" status
            binding.rainLotty.setVisibility(View.INVISIBLE);
            binding.isRaining.setText("");
        } else if (temperature >= 10 && temperature <= 13) {
            setNoHotWeather();
            binding.condition.setText("cloudy");
            rainMonitoring();
        } else if (temperature >= 7 && temperature < 10) {
            setNoHotWeather();
            binding.condition.setText("cold");
            rainMonitoring();
        } else if (temperature < 7) {
            setNoHotWeather();
            binding.condition.setText("too \nfrigid");
            snowMonitoring();
        }
    }

    private void rainMonitoring() {
        if (humidityC >= 60) {
            // It's rainy, show rain animation
            binding.rainLotty.setVisibility(View.VISIBLE);
            binding.isRaining.setText("It may rain");
            // Hide other weather animations
            binding.snowLotty.setVisibility(View.INVISIBLE);
            binding.sunLotty.setVisibility(View.INVISIBLE);
            binding.badWeatherSky.setVisibility(View.VISIBLE);
            binding.blueSky.setVisibility(View.INVISIBLE);
        } else {
            // It's not rainy, hide rain animation
            binding.rainLotty.setVisibility(View.INVISIBLE);
            binding.isRaining.setText(""); // Clear rain status
            // Show appropriate weather animations based on temperature
            if (temperature < 7) {
                isMaySnow();
            } else {
                setNoHotWeather();
            }
        }
    }

    private void snowMonitoring() {
        if (humidityC >= 60) {
            binding.snowLotty.setVisibility(View.INVISIBLE);
            binding.isRaining.setText("");
            dryWeather();
        } else {
            isMaySnow();
        }
    }

    public void setNoHotWeather() {
        binding.blueSky.setVisibility(View.INVISIBLE);
        binding.sunLotty.setVisibility(View.INVISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);
    }

    public  void dryWeather(){
        binding.rainLotty.setVisibility(View.INVISIBLE);
        binding.snowLotty.setVisibility(View.INVISIBLE);
        binding.blueSky.setVisibility(View.VISIBLE);
        binding.sunLotty.setVisibility(View.VISIBLE);
        binding.badWeatherSky.setVisibility(View.INVISIBLE);
    }

    public int makeFormFaringate(double tt) {
        return (int) (tt - 273.15);
    }
    @SuppressLint("SetTextI18n")
    private void isMaySnow() {
        binding.isRaining.setVisibility(View.VISIBLE);
        binding.isRaining.setText("It may \nsnow");
        binding.snowLotty.setVisibility(View.VISIBLE);
        binding.sunLotty.setVisibility(View.INVISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void isMayRain() {
        binding.isRaining.setVisibility(View.VISIBLE);
        binding.isRaining.setText("It may \nrain");
        binding.rainLotty.setVisibility(View.VISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);
        binding.sunLotty.setVisibility(View.INVISIBLE);
    }

    private void setNight() {
        binding.nightSky.setVisibility(View.VISIBLE);
        binding.blueSky.setVisibility(View.INVISIBLE);
        binding.sunLotty.setVisibility(View.INVISIBLE);
    }

    private void setDay() {
        binding.nightSky.setVisibility(View.INVISIBLE);
        binding.blueSky.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}