package io.github.iexpense;

import android.content.Context;

// This file implements some utility functions.
public class Utils {
    public static String getApplicationName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }
}
