package com.abood.mywallet.feature.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.abood.mywallet.databinding.ActivityMainBinding;
import com.abood.mywallet.feature.main.adapter.PageAdapter;
import com.abood.mywallet.feature.main.fragment.FinancialMovementsFragment;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.AppContent;
import com.abood.mywallet.utils.common.BaseActivity;

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
        initViewPager();
    }

    private void initViewPager() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new FinancialMovementsFragment(AppContent.DOLLAR, mainViewModel), AppContent.DOLLAR);
        pageAdapter.addFragment(new FinancialMovementsFragment(AppContent.DINAR, mainViewModel), AppContent.DINAR);
        pageAdapter.addFragment(new FinancialMovementsFragment(AppContent.SHEKEL, mainViewModel), AppContent.SHEKEL);
        getViewBinding().vpWallet.setAdapter(pageAdapter);
        getViewBinding().tlTabs.setupWithViewPager(getViewBinding().vpWallet);
    }

    @Override
    public void click() {
        getViewBinding().fabAdd.setOnClickListener(view -> mainViewModel.insert(new FinancialMovement(40
                , AppContent.DOLLAR, System.currentTimeMillis() / 1000)));
    }
}