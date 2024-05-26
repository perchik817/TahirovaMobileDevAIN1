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
import com.tahirova_ain1.shops.databinding.FragmentLoginBinding;
import com.tahirova_ain1.shops.models.CurrentUser;
import com.tahirova_ain1.shops.models.LoginResponse;
import com.tahirova_ain1.shops.remotedata.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    NavController navController;
    String emailUserIdentify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            if (!isEmptyLoginEditText()) {
                loginUser(new CurrentUser(binding.email.getText().toString(),
                        binding.passwd.getText().toString()));
            }
        });
        binding.btnReg.setOnClickListener(v2 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_navigation_login_to_navigation_register);
        });
    }

    private void loginUser(CurrentUser currentUser) {
        try{
            Call<LoginResponse> response = RetrofitClient.getInstance().getApi().checkLoginUser(currentUser);
            response.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful() && response.body() != null){

                        String tokenN = response.body().getAccessToken();
                        emailUserIdentify = binding.email.getText().toString();

                        Bundle bundle = new Bundle();
                        bundle.putString("identify", emailUserIdentify);
                        Bundle bundleToken = new Bundle();
                        bundleToken.putString("key_token", tokenN);
                        try {
                            SharedPreferences preferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefLoginEdit = preferences.edit();
                            prefLoginEdit.putBoolean("loggedin", true);
                            prefLoginEdit.putString("token", tokenN);
                            prefLoginEdit.commit();
                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                            navController.navigate(R.id.action_navigation_login_to_navigation_home);
                        } catch (Exception e) {
                            Log.d("API", "Token error" + e.getLocalizedMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                    Log.d("API", throwable.toString());
                }

            });
        }catch (Exception e){
            Log.d("API", e.toString());
            Toast.makeText(requireActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmptyLoginEditText() {
        if(binding.email.getText().toString().isEmpty() || binding.passwd.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getActivity(), "Empty Edit Text", Toast.LENGTH_SHORT);
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