package com.abood.mywallet.feature.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abood.mywallet.databinding.FragmentFinancialMovementBinding;
import com.abood.mywallet.feature.main.MainViewModel;
import com.abood.mywallet.feature.main.adapter.FinancialMovementAdapter;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.AppContent;
import com.abood.mywallet.utils.common.BaseFragment;

public class FinancialMovementsFragment extends BaseFragment<FragmentFinancialMovementBinding> {

    private String currency;
    private MainViewModel mainViewModel;

    public FinancialMovementsFragment(String title, MainViewModel mainViewModel) {
        this.currency = title;
        this.mainViewModel = mainViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewBinding(FragmentFinancialMovementBinding.inflate(getLayoutInflater()));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void data() {
        FinancialMovementAdapter adapter = new FinancialMovementAdapter(requireContext());
        getViewBinding().rvFinancialMovement.setLayoutManager(new LinearLayoutManager(requireContext()));
        getViewBinding().rvFinancialMovement.setItemAnimator(new DefaultItemAnimator());
        getViewBinding().rvFinancialMovement.setAdapter(adapter);

        mainViewModel.getFinancialMovementsByCurrency(currency).observe(this, financialMovements -> {
            adapter.clear();
            for (FinancialMovement financialMovement : financialMovements) {
                adapter.addItem(financialMovement);
            }
        });
        mainViewModel.getSumFinancialMovementsByCurrency(currency).observe(this, integer -> {
                    getViewBinding().tvBalance.setText((integer + " " + currency));
                });
    }

    @Override
    public void click() {

    }
}