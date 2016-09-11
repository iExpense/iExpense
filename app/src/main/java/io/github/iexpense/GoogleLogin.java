package io.github.iexpense;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLogin implements Login, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "GoogleLogin";
    private static final int RC_SIGN_IN = 9001;

    private boolean usingGLogin;
    private GoogleApiClient mGoogleApiClient;
    private AuthCredential mCredential;

    public GoogleLogin(FragmentActivity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        this.mCredential = null;
    }

    public boolean isSet() {
        return usingGLogin;
    }

    private void setUsingGLogin(boolean usingGLogin) {
        this.usingGLogin = usingGLogin;
    }

    public void registerOnClickListener(final Activity activity, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUsingGLogin(true);
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                activity.startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    public void signOut() {
        this.setUsingGLogin(false);
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Log.d(TAG, "signOut, status: " + status);
                    }
                });
    }

    public AuthCredential onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.d(TAG, "google:onFail :: " + result.getStatus());
                this.setUsingGLogin(false);
            }
        }

        return this.mCredential;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        this.setUsingGLogin(false);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle: " + acct.getId());
        this.mCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
    }
}
