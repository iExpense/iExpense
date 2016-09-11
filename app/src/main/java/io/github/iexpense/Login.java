package io.github.iexpense;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.google.firebase.auth.AuthCredential;

public interface Login {
    boolean isSet();
    void registerOnClickListener(Activity activity, View view);
    void signOut();
    AuthCredential onActivityResult(int requestCode, int resultCode, Intent data);
}
