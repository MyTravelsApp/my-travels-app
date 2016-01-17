package com.github.mytravelsapp.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mytravelsapp.R;
import com.github.mytravelsapp.presentation.di.HasComponent;
import com.github.mytravelsapp.presentation.di.components.CategoryComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerCategoryComponent;
import com.github.mytravelsapp.presentation.di.components.DaggerTravelComponent;
import com.github.mytravelsapp.presentation.di.components.TravelComponent;

/**
 * Created by stefani on 14/01/2016.
 */
public class CategoryActivity extends AbstractActivity implements HasComponent<CategoryComponent>{

    private CategoryComponent component;

    /**
     * Generate intent to open this activity.
     *
     * @param context Source context.
     * @return Intent.
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, CategoryActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Layout definition for activity
        setContentView(R.layout.activity_category);

        initializeInjector();
    }

    /**
     * Initialize DI components for this activity.
     */
    private void initializeInjector() {
        this.component = DaggerCategoryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }


    @Override
    public CategoryComponent getComponent() {
        return component;
    }
}
