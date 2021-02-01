package com.abood.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abood.mywallet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

 private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        binding.tvHello.setText("hi i am abood");
    }
}