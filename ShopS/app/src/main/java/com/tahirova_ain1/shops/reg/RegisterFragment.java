package com.tahirova_ain1.shops.reg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tahirova_ain1.shops.R;
import com.tahirova_ain1.shops.databinding.FragmentRegisterBinding;
import com.tahirova_ain1.shops.models.User;
import com.tahirova_ain1.shops.remotedata.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    User newUser;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnReg.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            if(!isEmptyEditTextReg()) registerToTable();
        });
        binding.tvLoginNow.setOnClickListener(v1 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_registr_to_navigation_login);
        });
    }

    private void registerToTable() {
        newUser = new User(binding.nameUser.getText().toString(),
                binding.email.getText().toString(),
                binding.passwd.getText().toString());
        Call<User> userCall = RetrofitClient.getInstance().getApi().registrationNewUser(newUser);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.body() != null){
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireActivity(), "Registration was successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefLoginEdit = preferences.edit();
                    prefLoginEdit.putBoolean("registration", true);
                    prefLoginEdit.commit();
                } else {
                    Log.e("fail", "fail");
                    Toast.makeText(requireActivity(), "Registration is not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.e("failure", "reg is failed");
                Toast.makeText(requireActivity(), "Reg is failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmptyEditTextReg() {
        if(binding.nameUser.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.passwd.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getActivity(), "Fill all field", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

