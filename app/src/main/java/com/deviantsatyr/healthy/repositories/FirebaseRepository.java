package com.deviantsatyr.healthy.repositories;

import com.deviantsatyr.healthy.repositories.base.Repository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.function.Consumer;

public class FirebaseRepository<T> implements Repository<T> {
    private final FirebaseFirestore mDB;
    private final Class<T> mKlass;
    private final String mCollectionName;

    public FirebaseRepository(Class<T> klass){
        mDB=FirebaseFirestore.getInstance();
        mKlass=klass;
        mCollectionName=klass.getSimpleName().toLowerCase();
        //T is Dining
        //Collection dining

        //T is Meal
        //Collection meal
    }

    @Override
    public void getAll(Consumer<List<T>> action) {
        mDB.collection(mCollectionName)
                .get()
                .addOnCompleteListener(task->{
                    List<T> items=task.getResult()
                            .toObjects(mKlass);
                    action.accept(items);
                });
    }

    @Override
    public void add(T item, Consumer<T> action) {
        mDB.collection(mCollectionName)
                .add(item)
                .addOnCompleteListener(task->{
                        action.accept(item);
                });
    }
}
