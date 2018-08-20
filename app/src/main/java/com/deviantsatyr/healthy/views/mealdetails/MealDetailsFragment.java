package com.deviantsatyr.healthy.views.mealdetails;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deviantsatyr.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealDetailsFragment extends Fragment {


    private String mMeal;
    private TextView mMealTextView;

    public MealDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_meal_details, container, false);
        mMealTextView=view.findViewById(R.id.tv_meal_name);
        mMealTextView.setText(mMeal);
        return view;
    }

    public static MealDetailsFragment instance() {
        return new MealDetailsFragment();
    }

    public void setMeal(String meal) {
        this.mMeal = meal;
        if(mMealTextView==null){
            return;
        }
        mMealTextView.setText(mMeal);
    }
}

