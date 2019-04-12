package ca.bcit.ass2.wang_thompson;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseReference database;
    DatabaseReference dbItems;
    DatabaseReference images;
    ArrayList<FeedItem> itemList;
    String headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = new ArrayList<FeedItem>();

        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database1.getReference("message");

        myRef.setValue("Hello, World!");

        // Write a message to the database
        database = FirebaseDatabase.getInstance().getReference("feeds");
        dbItems = database.child("items");
        images = dbItems.child("media");

        database.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                //the header of the JSON data
                headerTitle = dataSnapshot.child("title").getValue(String.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbItems.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){

                for(DataSnapshot item : dataSnapshot.getChildren()){
                    String title = item.child("title").getValue(String.class);
                    String imageUrl = "";

                    for (DataSnapshot iUrl : item.child("media").getChildren()){
                        imageUrl = iUrl.getValue(String.class);
                    }
                    String date = item.child("published").getValue(String.class);
                    String author = item.child("author").getValue(String.class);
                    FeedItem feedItem = new FeedItem(title, imageUrl, date, author);

                    //item list
                    itemList.add(feedItem);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
