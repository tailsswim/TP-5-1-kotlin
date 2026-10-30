package com.example.tp51;

import android.app.Activity;
import android.os.Build;

public class MainActivity extends Activity {
    if (Build.VERSION.SDK_INT >= 33) {
        requestPermissions(
                new String[]{
                        android.Manifest.permission.POST_NOTIFICATIONS
                },
                100
        );
    }
}
