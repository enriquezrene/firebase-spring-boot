package com.enriquezrene.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by rene on 15/09/16.
 */
class FireBaseRoot {

    FirebaseDatabase fireBaseDatabase
    DatabaseReference rootReference

    private void createRootReference(){
        rootReference = fireBaseDatabase.getReference("blog")
    }

    private static FireBaseRoot instance

    public static FireBaseRoot getInstance(){
        if(instance == null){
            instance = new FireBaseRoot()
        }
        return instance
    }

    private FireBaseRoot(){
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("src/main/resources/firebase-spring-boot-babbd2ea2d76.json"))
                .setDatabaseUrl("https://fir-spring-boot.firebaseio.com/")
                .build();
        FirebaseApp fireBaseApp = FirebaseApp.initializeApp(options);
        fireBaseDatabase = FirebaseDatabase.getInstance(fireBaseApp)
        createRootReference()
    }

}
