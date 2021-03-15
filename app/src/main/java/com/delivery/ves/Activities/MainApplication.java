package com.delivery.ves.Activities;

import android.app.Application;
import android.content.Context;

import com.delivery.ves.utils.LocaleHelper;

import java.util.Locale;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));

    }
}
