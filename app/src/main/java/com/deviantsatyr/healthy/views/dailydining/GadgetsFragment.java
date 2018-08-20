package com.deviantsatyr.healthy.views.dailydining;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.deviantsatyr.healthy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GadgetsFragment extends Fragment {
    int pStatus = 0;
    TextView mTVCalPercentage;

    public GadgetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gadgets, container, false);
        // Progress bar with text view>>
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular, getActivity().getTheme());
        final ProgressBar mProgress = view.findViewById(R.id.circularProgressbar);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);

      /*  ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(50000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();*/

        mTVCalPercentage = view.findViewById(R.id.tv);
        pStatus=66;

        mProgress.setProgress(pStatus);
        mTVCalPercentage.setText(pStatus + "%");
        //<<
        return view;
    }

    public static GadgetsFragment instance() {
        return new GadgetsFragment();
    }
}

