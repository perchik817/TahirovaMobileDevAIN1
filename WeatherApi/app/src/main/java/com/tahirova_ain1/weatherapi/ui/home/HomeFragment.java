package com.tahirova_ain1.weatherapi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

    final String apiKey = WeatherApi.URL;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.localTime.setText(currentTime);
        Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather("Bishkek", apiKey);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful() && response.body() != null){
                    Main mainModel = response.body().getMain();
                    Wind windModel = response.body().getWind();
                    Clouds cloudsModel = response.body().getClouds();
                    Sys sysModel = response.body().getSys();

                    Double temp = mainModel.getTemp();
                    Double tempMax = mainModel.getTempMax();
                    Double tempMin = mainModel.getTempMin();

                    temperature = makeFromFaringate(temp);
                    temperatureMax = makeFromFaringate(tempMax);
                    temperatureMin = makeFromFaringate(tempMin);
                    setCondition();

                    binding.tempC.setText(String.valueOf(temperature) + " °C");
                    binding.tempMaxMin.setText(String.valueOf(temperatureMax) + " °C↑\n" +
                            String.valueOf(temperatureMin) + " °C↓");
                    binding.cityName.setText("Bishkek");
                    binding.humidity.setText(mainModel.getHumidity() + " %");
                    binding.pressure.setText(mainModel.getPressure() + "\nmBar");
                    binding.wind.setText(windModel.getSpeed() + " m/s");
                    binding.cloud.setText(cloudsModel.getAll() + " %");
                    binding.sunrise.setText(String.valueOf(getCurrentDateTime(sysModel.getSunrise())));
                    binding.sunset.setText(String.valueOf(getCurrentDateTime(sysModel.getSunset())));
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable throwable) {
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


        });
        binding.search.setOnClickListener(v1 -> {
            if(binding.inputCity.getText().){
                Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather("Bishkek", apiKey);
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if(response.isSuccessful() && response.body() != null){
                            Main mainModel = response.body().getMain();
                            Wind windModel = response.body().getWind();
                            Clouds cloudsModel = response.body().getClouds();
                            Sys sysModel = response.body().getSys();

                            Double temp = mainModel.getTemp();
                            Double tempMax = mainModel.getTempMax();
                            Double tempMin = mainModel.getTempMin();

                            temperature = makeFromFaringate(temp);
                            temperatureMax = makeFromFaringate(tempMax);
                            temperatureMin = makeFromFaringate(tempMin);
                            setCondition();

                            binding.tempC.setText(String.valueOf(temperature) + " °C");
                            binding.maxMinTemp.setText(String.valueOf(temperatureMax) + " °C↑\n" +
                                    String.valueOf(temperatureMin) + " °C↓");
                            binding.cityName.setText("Bishkek");
                            binding.humidity.setText(mainModel.getHumidity() + " %");
                            binding.pressure.setText(mainModel.getPressure() + "\nmBar");
                            binding.wind.setText(windModel.getSpeed() + " m/s");
                            binding.cloudy.setText(cloudsModel.getAll() + " %");
                            binding.sunrise.setText(String.valueOf(getCurrentDateTime(sysModel.getSunrise())));
                            binding.sunset.setText(String.valueOf(getCurrentDateTime(sysModel.getSunset())));
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable throwable) {
                        Toast.makeText(requireActivity(), "Input city's name" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}