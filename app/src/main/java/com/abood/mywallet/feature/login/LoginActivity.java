package com.abood.mywallet.feature.login;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.abood.mywallet.R;
import com.abood.mywallet.databinding.ActivityLoginBinding;
import com.abood.mywallet.feature.main.MainActivity;
import com.abood.mywallet.utils.AppContent;
import com.abood.mywallet.utils.NavigateUtils;
import com.abood.mywallet.utils.common.BaseActivity;
import com.abood.mywallet.utils.fingerprint.FingerPrint;
import com.abood.mywallet.utils.fingerprint.FingerPrint.FingerprintListener;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements FingerprintListener {

    private String password = "123456";
    private FingerPrint fingerPrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setViewBinding(ActivityLoginBinding.inflate(getLayoutInflater()));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void data() {
        fingerPrint = new FingerPrint(this, this);
        fingerPrint.checkFingerprint();
    }

    @Override
    public void click() {
        getViewBinding().btnFingerprint.setOnClickListener(view -> {
            fingerPrint.UseFingerprint();
        });

        getViewBinding().etPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (getViewBinding().etPassword.getText().toString().trim()
                        .equals(password)) {
                    toMain();
                } else {
                    getViewBinding().etPassword.setError(getString(R.string.wrong_password));
                }
            }
            return false;
        });
    }

    @Override
    public void isAvailable(boolean available, String msg) {
        if (!available) {
            getViewBinding().btnFingerprint.setVisibility(View.INVISIBLE);
            getViewBinding().tvStatus.setText(msg);
        }
    }

    @Override
    public void successToAccess(boolean success, String msg) {
        if (success) {
            toMain();
        } else {
            getViewBinding().tvStatus.setText(msg);
        }
    }

    private void toMain() {
        if (getLocalStorage().getObject(AppContent.ARRAY_CURRENCY) == null) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(AppContent.DOLLAR);
            strings.add(AppContent.DINAR);
            strings.add(AppContent.SHEKEL);
            getLocalStorage().setObject(AppContent.ARRAY_CURRENCY, strings);
        }
        NavigateUtils.activityIntent(this, MainActivity.class, false);
    }

}