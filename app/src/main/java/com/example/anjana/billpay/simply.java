package com.example.anjana.billpay;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class simply extends AppCompatActivity {

    int i;
    ArrayList<String> a;
    ArrayList<String> b=new ArrayList<>();
    ArrayList<String> c=new ArrayList<>();
    //ListView lv;
    String ding,wastefellow;
    int count=0;
//Intent divya=new Intent(simply.this,third.class);

    //  ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simply);
        DatabaseReference root= FirebaseDatabase.getInstance().getReference();
        DatabaseReference aaa,bbb;

        a= (ArrayList<String>) getIntent().getSerializableExtra("key");

        ProgressBar pb=(ProgressBar)findViewById(R.id.progressBar3);


//aaa = root.child("items").child(a.get(i)).child("cost");

        aaa = root.child(a.get(i)).child("cost");
        bbb = root.child(a.get(i)).child("name");
        //Toast.makeText(getApplicationContext(),a.get(0),Toast.LENGTH_SHORT).show();


        root.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ding = dataSnapshot.getValue().toString();

                for(i=0;i<a.size();i++){

                    b.add(dataSnapshot.child(a.get(i)).child("cost").getValue().toString());
                }

                op();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        root.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                wastefellow = dataSnapshot.getValue().toString();

                for(i=0;i<a.size();i++){

                    c.add(dataSnapshot.child(a.get(i)).child("name").getValue().toString());
                }

                op();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void op() {
        Intent kl=new Intent(simply.this,third.class);
        kl.putExtra("rohith",b);
        kl.putExtra("qwerty",c);
        startActivity(kl);
    }

}

