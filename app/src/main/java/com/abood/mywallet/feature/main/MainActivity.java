package com.abood.mywallet.feature.main;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.abood.mywallet.databinding.ActivityMainBinding;
import com.abood.mywallet.dialog.AddFinancialMovement;
import com.abood.mywallet.feature.main.adapter.PageAdapter;
import com.abood.mywallet.feature.main.fragment.FinancialMovementsFragment;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.AppContent;
import com.abood.mywallet.utils.common.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setViewBinding(ActivityMainBinding.inflate(getLayoutInflater()));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void data() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //Log.e(getClass().getName() + " : financial", getLocalStorage().getObject(AppContent.ARRAY_CURRENCY).toString());
        initViewPager();
    }

    private void initViewPager() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        for (String s : (ArrayList<String>) getLocalStorage().getObject(AppContent.ARRAY_CURRENCY)) {
            pageAdapter.addFragment(new FinancialMovementsFragment(s, mainViewModel), s);

        }

        getViewBinding().vpWallet.setAdapter(pageAdapter);
        getViewBinding().tlTabs.setupWithViewPager(getViewBinding().vpWallet);
    }

    @Override
    public void click() {
        getViewBinding().fabAdd.setOnClickListener(view -> {
            AddFinancialMovement addFinancialMovement = new AddFinancialMovement(mainViewModel);
            addFinancialMovement.show(getSupportFragmentManager(), "");
        });
    }
}