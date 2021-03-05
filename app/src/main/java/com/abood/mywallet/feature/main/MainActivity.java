package com.abood.mywallet.feature.main;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.abood.mywallet.databinding.ActivityMainBinding;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.common.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.setRootView(binding.getRoot());
        super.onCreate(savedInstanceState);

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
        mainViewModel.getAllFinancialMovement().observe(this, new Observer<List<FinancialMovement>>() {
            @Override
            public void onChanged(List<FinancialMovement> financialMovements) {
                //update recycle view
                binding.tvTest.setText(financialMovements.toString());
            }
        });
    }

    @Override
    public void click() {

    }
}