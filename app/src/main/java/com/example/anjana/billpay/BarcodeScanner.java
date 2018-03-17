package com.example.anjana.billpay;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class BarcodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ArrayList<String> dr = new ArrayList<String>();

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    String scanResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(BarcodeScanner.this, "Permission is granted!",Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }

        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(BarcodeScanner.this, CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermssionsResult(int requestCode, String permission[] , int grantResults[])
    {
        switch(requestCode)
        {
            case REQUEST_CAMERA :
                if (grantResults.length>0)
                {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted)
                    {
                        Toast.makeText(BarcodeScanner.this, "Permission granted",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(BarcodeScanner.this, "Permission denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;

                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                if(scannerView==null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestPermission();
            }

        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }
    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener)
    {
        new AlertDialog.Builder(BarcodeScanner.this)
                .setMessage(message)
                .setPositiveButton("OK",listener)
                .setNegativeButton("Cancel",null)
                .create()
                .show();
    }



    @Override
    public void handleResult(Result result) {

        scanResult = result.getText();
        dr.add(scanResult);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Scaner Result");
        builder.setPositiveButton("Add Another", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {






//                for(int j=0;j<dr.size();j++){
//                    Toast.makeText(getApplicationContext(),dr.get(j),Toast.LENGTH_SHORT).show();
//                }


//                Intent toy=new Intent(MainActivity.this,third.class);






                onResume();

                // startActivity(toy);




            }
        });




        builder.setNeutralButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Intent intent=new Intent(BarcodeScanner.this,simply.class);
                // Bundle b=new Bundle();
                //  b.putStringArrayList("lolol",dr);


                intent.putExtra("key",dr);


                startActivity(intent);
            }
        });



        builder.setMessage(scanResult);
        AlertDialog alert=builder.create();
        alert.show();
    }
}