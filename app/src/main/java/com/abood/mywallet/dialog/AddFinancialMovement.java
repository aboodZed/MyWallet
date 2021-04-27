package com.abood.mywallet.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abood.mywallet.R;
import com.abood.mywallet.databinding.DialogAddFinancialMovementBinding;
import com.abood.mywallet.dialog.adapter.SpinnerAdapter;
import com.abood.mywallet.feature.main.MainViewModel;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.AppContent;
import com.abood.mywallet.utils.common.BaseDialog;

import java.util.ArrayList;

public class AddFinancialMovement extends BaseDialog<DialogAddFinancialMovementBinding> {

    private MainViewModel mainViewModel;

    public AddFinancialMovement(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setViewBinding(DialogAddFinancialMovementBinding.inflate(getLayoutInflater()));
        setSettings(80, true, true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void data() {
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(requireContext()
                , (ArrayList<String>) getLocalStorage().getObject(AppContent.ARRAY_CURRENCY));
        getViewBinding().sCurrency.setAdapter(spinnerAdapter);
    }

    @Override
    public void click() {
      getViewBinding().btnAdd.setOnClickListener(view -> {
          saveFinancialMovement();
      });
    }

    private void saveFinancialMovement() {
        String value = getViewBinding().etValue.getText().toString();
        if (TextUtils.isEmpty(value)) {
            getViewBinding().etValue.setError(getString(R.string.required));
            return;
        }
        mainViewModel.insert(new FinancialMovement(Double.parseDouble(value)
                , (String) getViewBinding().sCurrency.getSelectedItem()
                , (System.currentTimeMillis() / 1000)));
        dismiss();
    }
}
