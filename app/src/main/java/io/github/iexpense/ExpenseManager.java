package io.github.iexpense;

import com.google.firebase.database.FirebaseDatabase;

// This class implements functions to interact with Firebase.
// It provides functions to save, retrieve, delete data from
// Firebase Realtime Database.
public class ExpenseManager {
    private static final String TAG = "ExpenseManager";
    private static final String FB_KEY_EXPENSES = "expenses";

    private static FirebaseDatabase cDatabase;

    static {
        cDatabase = FirebaseDatabase.getInstance();
        cDatabase.setPersistenceEnabled(true);
    }

//    public static void getExpenses(FirebaseAuth mAuth, FireBaseCallback fireBaseCallback) {
//        final String userId = mAuth.getCurrentUser().getUid();
//        DatabaseReference dbRefToExpenses = cDatabase.getReference().child(userId).child(FB_KEY_EXPENSES);
//        if (dbRefToExpenses == null) {
//            Log.w(TAG, "User "+userId+" not found");
//            return;
//        }
//
//        dbRefToExpenses.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "getTopics:onCancelled", databaseError.toException());
//            }
//        });
//    }
//
//    public void saveUserData(FirebaseAuth mAuth, FireBaseCallback fireBaseCallback) {
//        DatabaseReference dbRefToAuth = cDatabase.getReference().child(userId).child(FB_KEY_AUTH);
//        dbRefToAuth.setValue();
//    }
}
