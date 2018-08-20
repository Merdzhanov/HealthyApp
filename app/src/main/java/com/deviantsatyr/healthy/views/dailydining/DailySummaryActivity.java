package com.deviantsatyr.healthy.views.dailydining;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.deviantsatyr.healthy.R;
import com.deviantsatyr.healthy.authentication.ChooserActivity;
import com.deviantsatyr.healthy.uiutils.Navigator;
import com.deviantsatyr.healthy.views.mealdetails.MealDetailsActivity;
import com.deviantsatyr.healthy.views.mealdetails.MealDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

//public class DailySummaryActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daily_summary);
//    }
//}
//package com.deviantsatyr.healthy.views;
//
//        import android.app.Activity;
//        import android.app.FragmentTransaction;
//        import android.content.Intent;
//
//        import android.os.Bundle;
//        import android.view.View;
//
//
//        import com.deviantsatyr.healthy.uiutils.Navigator;
//        import com.google.firebase.auth.FirebaseAuth;
//        import com.google.firebase.auth.FirebaseUser;

public class DailySummaryActivity extends Activity implements Navigator {
    private static final int RC_SIGN_IN = 9001;


    private DailyMealsListFragment mDailyMealsListFragment;
    private GadgetsFragment mGadgetsFragment;
    private View mButtonNavigate;
    private boolean mIsPhone;
    private MealDetailsFragment mMealDetailsFragment;
    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_summary);
        mAuth = FirebaseAuth.getInstance();
        mToolbar=findViewById(R.id.drawer_toolbar);


//        mButtonNavigate=findViewById(R.id.button_navigate);
//        mButtonNavigate.setOnClickListener(view->{
//            Intent intent=new Intent(this,MealDetailsActivity.class);
//            startActivity(intent);
//        });

        mGadgetsFragment = GadgetsFragment.instance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content_gadgets, mGadgetsFragment)
//                .commit();

        mDailyMealsListFragment = DailyMealsListFragment.instance();
        mDailyMealsListFragment.setNavigator(this);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content, mDailyMealsListFragment)
//                .commit();

        mIsPhone = findViewById(R.id.content_detail) == null;

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.content_gadgets, mGadgetsFragment);
        transaction.replace(R.id.content, mDailyMealsListFragment);

        if (!mIsPhone) {
            mMealDetailsFragment = MealDetailsFragment.instance();
            transaction.replace(R.id.content_detail, mMealDetailsFragment);
        }
        transaction.commit();

        setupDrawer();
    }

    @Override
    public void navigateWith(String meal) {
        if (mIsPhone) {
            Intent intent = new Intent(this, MealDetailsActivity.class);
            intent.putExtra("MEAL_NAME", meal);
            startActivity(intent);
        } else {
            mMealDetailsFragment.setMeal(meal);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
    @Override
    public void onStart() {
        super.onStart();

        // Start sign in if necessary
        if (shouldStartSignIn()) {
            startSignIn();
        }
    }

    ////////////////////////
    private void updateUI(FirebaseUser currentUser) {
    }

    private boolean shouldStartSignIn() {
       // return (!mViewModel.getIsSigningIn() &&
        return FirebaseAuth.getInstance().getCurrentUser() == null;
    }

    private void startSignIn() {
        // Sign in with FirebaseUI
        Intent intent = new Intent(this, ChooserActivity.class);
        //intent.putExtra("MEAL_NAME", meal);
        startActivityForResult(intent, RC_SIGN_IN);
        //startActivity(intent);

//        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
//                .setAvailableProviders(Collections.singletonList(
//                        new AuthUI.IdpConfig.EmailBuilder().build()))
//                .setIsSmartLockEnabled(false)
//                .build();
//
//        startActivityForResult(intent, RC_SIGN_IN);
       // mViewModel.setIsSigningIn(true);
    }

    public void setupDrawer(){
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem listDining = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Daily dining");
        PrimaryDrawerItem createDining = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("New dining");

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        listDining,
                        new DividerDrawerItem(),
                        createDining//,
                        //new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        // do something with the clicked item :D
//                    }
//                })
                .build();
    }
}

