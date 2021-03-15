package com.delivery.ves.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANG="selected";

    public static Context onAttach(Context context){
        String lang=getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context,lang);
    }

    public static Context onAttach(Context context, String defaultLanguage){
        String lang=getPersistedData(context, defaultLanguage);
        return setLocale(context,lang);
    }

    public static Context setLocale(Context context, String lang) {
        persist(context,lang);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            return updateResources(context,lang);
        }

        return updateResourcesLegacy(context,lang);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);

        Resources resources=context.getResources();
        DisplayMetrics dm=resources.getDisplayMetrics();
        Configuration config=resources.getConfiguration();
//        config.locale=locale;

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(lang.toLowerCase()));
            config.setLayoutDirection(locale);
        }
        resources.updateConfiguration(config,dm);
        return context;

    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context updateResources(Context context, String lang) {

        Locale locale=new Locale(lang);
        locale.setDefault(locale);
        Resources resources=context.getResources();
        Configuration config=resources.getConfiguration();
        config.locale=locale;
        config.setLayoutDirection(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
        return context.createConfigurationContext(config);
    }

    private static void persist(Context context, String lang) {

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(SELECTED_LANG,lang);
        editor.apply();
    }

    public static String getPersistedData(Context context, String language) {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString(SELECTED_LANG,language);
    }
}
