package com.example.anjana.billpay;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ownerOne extends AppCompatActivity{

public Button add;
public EditText name,itemid,price;
DatabaseReference databaseitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owneractivity);
        databaseitems=FirebaseDatabase.getInstance().getReference("items");
        name=(EditText)findViewById(R.id.name);
        itemid=(EditText)findViewById(R.id.itemid);
        price=(EditText)findViewById(R.id.price);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem();
            }
        });


    }
private void additem(){
        String itemname=name.getText().toString().trim();
        String itemprice=price.getText().toString().trim();
        if(!TextUtils.isEmpty(itemname)){
            String idd=databaseitems.push().getKey();
            Itemss itemss=new Itemss(itemname,idd,itemprice);
            databaseitems.child(idd).setValue(itemss);
            Toast.makeText(this,"Item added",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(),"enter item name",Toast.LENGTH_LONG).show();

}

}
