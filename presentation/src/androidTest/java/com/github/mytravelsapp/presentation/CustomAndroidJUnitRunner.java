package com.github.mytravelsapp.presentation;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.github.mytravelsapp.presentation.MockAndroidApplication;

/**
 * Created by kisco on 09/02/2016.
 */
public class CustomAndroidJUnitRunner  extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockAndroidApplication.class.getName(), context);
    }
}
