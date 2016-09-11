package io.github.iexpense;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.facebook.FacebookSdk;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;

public class FacebookLogin implements Login {
    private static final String TAG = "FacebookLogin";

    private boolean usingFBLogin;
    private CallbackManager mCallbackManager;
    private AuthCredential mCredential;

    public FacebookLogin(Activity activity) {
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        AppEventsLogger.activateApp(activity.getApplication());

        this.usingFBLogin = false;
        this.mCallbackManager = CallbackManager.Factory.create();
        this.mCredential = null;
    }

    public boolean isSet() {
        return usingFBLogin;
    }

    private void setUsingFBLogin(boolean usingFBLogin) {
        this.usingFBLogin = usingFBLogin;
    }

    public void registerOnClickListener(Activity activity, View view) {
        LoginButton loginButton = (LoginButton) view;
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess: " + loginResult);
                AccessToken token = loginResult.getAccessToken();
                setUsingFBLogin(true);
                handleToken(token);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                setUsingFBLogin(false);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError :: ", error);
                setUsingFBLogin(false);
            }
        });
    }

    public void signOut() {
        setUsingFBLogin(false);
        LoginManager.getInstance().logOut();
    }

    public AuthCredential onActivityResult(int requestCode, int resultCode, Intent data) {
        this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        return this.mCredential;
    }

    private void handleToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken: " + token);
        this.mCredential = FacebookAuthProvider.getCredential(token.getToken());
    }
}
