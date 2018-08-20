package com.deviantsatyr.healthy.views.dailydining;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.deviantsatyr.healthy.R;
import com.deviantsatyr.healthy.models.Dining;
import com.deviantsatyr.healthy.repositories.FirebaseRepository;
import com.deviantsatyr.healthy.repositories.base.Repository;
import com.deviantsatyr.healthy.uiutils.Navigator;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyMealsListFragment extends Fragment implements AdapterView.OnItemClickListener{
    private Navigator navigator;
    private ArrayAdapter<String> mDailyMealsAdapter;
    private ListView mDailyMealsListView;
    private Navigator mNavigator;
    private FirebaseFirestore mDB;
    private Repository<Dining> mDailyMealsRepository;

    public DailyMealsListFragment() {
        // Required empty public constructor
    }
    public static DailyMealsListFragment instance() {
        return new DailyMealsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_daily_meals_list, container, false);


        mDailyMealsListView = view.findViewById(R.id.lv_daily_meals);
        mDailyMealsAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        mDailyMealsListView.setAdapter(mDailyMealsAdapter);
        mDailyMealsListView.setOnItemClickListener(this);

        mDB = FirebaseFirestore.getInstance();
//        mDB.collection("dining")
//                .get()
//                .addOnCompleteListener(task-> {
//                            List<Dining> dinings = task.getResult()
//                                    .toObjects(Dining.class);
//
//                            for (Dining dining : dinings) {
//                                mDailyMealsAdapter.add(dining.meal_name);
//                            }
//                        }
//                );

        mDailyMealsRepository= new FirebaseRepository<>(Dining.class);

        mDailyMealsRepository.add(new Dining("20.08.2018","pig"),
                newDining->{ mDailyMealsRepository.getAll(dinings-> {
                    for (Dining dining : dinings) {
                        mDailyMealsAdapter.add(dining.meal_name);
                    }
                });
        });
//        Dining dining=new Dining("20.08.2018","egg");
//        mDB.collection("dining").add(dining);

//        mDB.collection("dining")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        //for (QueryDocumentSnapshot document:task.getResult()){
//                        List<Dining> dinings = task.getResult().toObjects(Dining.class);
//                        for (Dining dining: dinings) {
//                            mDailyMealsAdapter.add(dining.meal_name);
//                        }
//                        //}
//                    }
//                });
       /// mDailyMealsAdapter.add("Egg");
       // mDailyMealsAdapter.add("Cheese");

//        Dining dining=new Dining("20.08.2018","egg");
//        mDB.collection("dining").add(dining);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //startActivity();
        //adapterView.navigate();
        //Toast.makeText(getContext(), mDailyMealsAdapter.getItem(i), Toast.LENGTH_SHORT).show();
        String meal=mDailyMealsAdapter.getItem(i);
        mNavigator.navigateWith(meal);
    }

    public void setNavigator(Navigator navigator){
        mNavigator=navigator;
    }
}

