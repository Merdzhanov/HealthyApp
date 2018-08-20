package com.deviantsatyr.healthy.views.mealdetails;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.deviantsatyr.healthy.R;

public class MealDetailsActivity extends Activity {
    private MealDetailsFragment mMealDetailsFragment;

    // private MealDetailsFragment mMealDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        Intent intent=getIntent();
        String meal=intent.getStringExtra("MEAL_NAME");

        mMealDetailsFragment=MealDetailsFragment.instance();
       getFragmentManager()// getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mMealDetailsFragment)
                .commit();
        mMealDetailsFragment.setMeal(meal);
//        Intent intent=getIntent();
//        String meal=intent.getStringExtra("MEAL_NAME");
//        TextView tv=findViewById(R.id.tv_meal_name);
//        tv.setText(meal);
    }
}
