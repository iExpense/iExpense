package io.github.iexpense;

import com.google.firebase.database.FirebaseDatabase;

// This class implements functions to interact with Firebase.
// It provides functions to save, retrieve, delete data from
// Firebase Realtime Database.
public class ExpenseManager {
    private static final String TAG = "ExpenseManager";
    private static FirebaseDatabase cDatabase;

    static {
        cDatabase = FirebaseDatabase.getInstance();
        cDatabase.setPersistenceEnabled(true);
    }
}
