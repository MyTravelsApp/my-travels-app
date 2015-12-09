package com.github.mytravelsapp.presentation.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Custom scope for dagger.
 *
 * @author fjtorres
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
