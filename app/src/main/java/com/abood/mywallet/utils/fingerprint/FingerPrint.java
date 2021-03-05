package com.abood.mywallet.utils.fingerprint;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.abood.mywallet.R;

import java.util.concurrent.Executor;

public class FingerPrint {

    private Activity activity;
    private FingerprintListener listener;

    public FingerPrint(Activity activity, FingerprintListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    public void checkFingerprint() {
        BiometricManager biometricManager = BiometricManager.from(activity);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                listener.isAvailable(true, activity.getString(R.string.success_fingerprint_access));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                listener.isAvailable(false, activity.getString(R.string.no_hardware));
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                listener.isAvailable(false, activity.getString(R.string.un_available));
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                listener.isAvailable(false, activity.getString(R.string.no_fingerprint_saved));
                break;
        }
    }

    public void UseFingerprint() {
        Executor executor = ContextCompat.getMainExecutor(activity);

        BiometricPrompt biometricPrompt = new BiometricPrompt((FragmentActivity) activity, executor
                , new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                listener.successToAccess(false, errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                listener.successToAccess(true, result.toString());
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                listener.successToAccess(false, activity.getString(R.string.failed_fingerprint_access));
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(activity.getString(R.string.access))
                .setDescription(activity.getString(R.string.use_fingerprint))
                .setNegativeButtonText(activity.getString(R.string.cancel))
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    public interface FingerprintListener {

        void isAvailable(boolean available, String msg);
        void successToAccess(boolean success, String msg);
    }
}
