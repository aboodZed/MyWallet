package com.abood.mywallet.utils.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.abood.mywallet.utils.AppController;
import com.abood.mywallet.utils.LocalStorage;

public abstract class BaseActivity<T extends ViewBinding>
        extends AppCompatActivity implements BaseView {

    private T viewBinding;

    public void setViewBinding(T viewBinding) {
        this.viewBinding = viewBinding;
    }

    public T getViewBinding() {
        return viewBinding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(viewBinding.getRoot());
        super.onCreate(savedInstanceState);
        data();
        click();
    }

    public LocalStorage getLocalStorage() {
        return AppController.getInstance().getLocalStorage();
    }
}
