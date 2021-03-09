package com.abood.mywallet.utils.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.abood.mywallet.utils.AppController;
import com.abood.mywallet.utils.LocalStorage;

public abstract class BaseFragment<T extends ViewBinding>
        extends Fragment implements BaseView {

    private T viewBinding;

    public T getViewBinding() {
        return viewBinding;
    }

    public void setViewBinding(T viewBinding) {
        this.viewBinding = viewBinding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        data();
        click();
        return viewBinding.getRoot();
    }

    public LocalStorage getLocalStorage() {
        return AppController.getInstance().getLocalStorage();
    }
}
