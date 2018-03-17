package com.example.anjana.billpay;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class third extends AppCompatActivity {

    int sum=0;
    Button amount;

    ListView lv2;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);




        ArrayList<String> e= (ArrayList<String>) getIntent().getSerializableExtra("rohith");
        ArrayList<String> f= (ArrayList<String>) getIntent().getSerializableExtra("qwerty");

        ArrayList<String > g=new ArrayList<>();

        for(int i=0;i<e.size();i++)
        {
            g.add(e.get(i)+"    "+f.get(i));
        }

        for(int i=0;i<e.size();i++) {
            sum = sum + Integer.parseInt(e.get(i));
        }
        Button amount=(Button)findViewById(R.id.amount);
        amount.setText("Total Cost : " + sum);

        lv2= (ListView) findViewById(R.id.lv2);
        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview, R.id.textView,g);
        lv2.setAdapter(adapter);


    }
}

