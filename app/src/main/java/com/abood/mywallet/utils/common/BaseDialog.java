package com.abood.mywallet.utils.common;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewbinding.ViewBinding;

import com.abood.mywallet.R;
import com.abood.mywallet.utils.AppController;
import com.abood.mywallet.utils.LocalStorage;

abstract class BaseDialog<T extends ViewBinding> extends DialogFragment implements BaseView {

    private T viewBinding;
    private double width;
    private boolean allowDismiss;
    private boolean transparent;

    public T getViewBinding() {
        return viewBinding;
    }

    public void setViewBinding(T viewBinding) {
        this.viewBinding = viewBinding;
    }

    public void setSettings(double width, boolean allowDismiss, boolean transparent) {
        this.width = width;
        this.allowDismiss = allowDismiss;
        this.transparent = transparent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        data();
        click();
        return viewBinding.getRoot();
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * width / 100);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        if (transparent)
            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        setCancelable(false);
        super.onResume();
        if (allowDismiss)
            getDialog().setOnKeyListener((dialog, keyCode, event) -> {
                if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                    this.dismiss();
                    return true;
                } else return false;
            });
    }

    public LocalStorage getLocalStorage() {
        return AppController.getInstance().getLocalStorage();
    }
}
