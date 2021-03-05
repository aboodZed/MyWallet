package com.abood.mywallet.feature.login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.abood.mywallet.R;
import com.abood.mywallet.databinding.ActivityLoginBinding;
import com.abood.mywallet.feature.main.MainActivity;
import com.abood.mywallet.utils.NavigateUtils;
import com.abood.mywallet.utils.UIUtils;
import com.abood.mywallet.utils.common.BaseActivity;
import com.abood.mywallet.utils.fingerprint.FingerPrint;
import com.abood.mywallet.utils.fingerprint.FingerPrint.FingerprintListener;

public class LoginActivity extends BaseActivity implements FingerprintListener {

    private ActivityLoginBinding binding;
    private String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.setRootView(binding.getRoot());
        super.onCreate(savedInstanceState);
        //layout binding
    }

    @Override
    public void click() {
        FingerPrint fingerPrint = new FingerPrint(this, this);
        fingerPrint.checkFingerprint();
        binding.btnFingerprint.setOnClickListener(view -> {
            fingerPrint.UseFingerprint();
        });

        binding.etPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (binding.etPassword.getText().toString().trim()
                        .equals(password)) {
                    toMain();
                }else {
                    binding.etPassword.setError(getString(R.string.wrong_password));
                }
            }
            return false;
        });
    }

    private void toMain() {
        NavigateUtils.activityIntent(this, MainActivity.class, false);
    }

    @Override
    public void isAvailable(boolean available, String msg) {
        if (!available) {
            binding.btnFingerprint.setVisibility(View.INVISIBLE);
            binding.tvStatus.setText(msg);
        }
    }

    @Override
    public void successToAccess(boolean success, String msg) {
        if (success) {
            toMain();
        } else {
            binding.tvStatus.setText(msg);
        }
    }
}