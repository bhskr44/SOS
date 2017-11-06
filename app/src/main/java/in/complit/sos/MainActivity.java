 package in.complit.sos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.y;

 public class MainActivity extends AppCompatActivity {
            EditText e1,e2,e3,e4,e5;
            String s1,s2,s3,s4,s5;
            GPSTracker gps;
            private static final int REQUEST_CODE_PERMISSION = 2;
            String[] mPermission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_BOOT_COMPLETED};
            double latitude,longitude;
            static String str1,str2,str3,str4,str5;
            int i=0;
            static String phone1,phone2,phone3,phone4,phone5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.ed1);
        e2 = (EditText) findViewById(R.id.ed2);
        e3 = (EditText) findViewById(R.id.ed3);
        e4 = (EditText) findViewById(R.id.ed4);
        e5 = (EditText) findViewById(R.id.ed5);
        startService(new Intent(this, UpdateService.class));

        DB dbhelper = new DB(getApplicationContext());

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission[0]},
                        REQUEST_CODE_PERMISSION);

                if (ActivityCompat.checkSelfPermission(this, mPermission[1])
                        != MockPackageManager.PERMISSION_GRANTED)

                    ActivityCompat.requestPermissions(this, new String[]{mPermission[1]},
                            REQUEST_CODE_PERMISSION);

                if (ActivityCompat.checkSelfPermission(this, mPermission[2])
                        != MockPackageManager.PERMISSION_GRANTED)

                    ActivityCompat.requestPermissions(this, new String[]{mPermission[2]},
                            REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cursor c = dbhelper.getdata1();
        while (c.moveToNext()) {

            str1 = c.getString(c.getColumnIndex(DB.col1));
            str2 = c.getString(c.getColumnIndex(DB.col2));
            str3 = c.getString(c.getColumnIndex(DB.col3));
            str4 = c.getString(c.getColumnIndex(DB.col4));
            str5 = c.getString(c.getColumnIndex(DB.col5));
        }
        e1.setText(str1);e2.setText(str2);e3.setText(str3);e4.setText(str4);e5.setText(str5);
    }

    public void save1(View view) {
        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = e3.getText().toString();
        s4 = e4.getText().toString();
        s5 = e5.getText().toString();
        if(s1.equals("")||s2.equals("")||s3.equals("")){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("First 3 Fields are mandatory.");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(MainActivity.this,"For better reference add five.",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        else{
            DB database= new DB(getApplicationContext());
        //str1=s1;str2=s2;str3=s3;str4=s4;str5=s5;
        long c=database.insertphone(s1,s2,s3,s4,s5);
        if (c > 0)
        {

            Toast.makeText(getApplicationContext(),"Numbers Saved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error.!!",Toast.LENGTH_SHORT).show();
        }
    }

    }
    public void test(View view) {
        DB myDBhelper = new DB(getApplicationContext());
        Cursor c = myDBhelper.getdata1();

        while (c.moveToNext()) {

            phone1 = c.getString(c.getColumnIndex(DB.col1));
            phone2 = c.getString(c.getColumnIndex(DB.col2));
            phone3 = c.getString(c.getColumnIndex(DB.col3));
            phone4 = c.getString(c.getColumnIndex(DB.col4));
            phone5 = c.getString(c.getColumnIndex(DB.col5));
        }
        Toast.makeText(getApplicationContext(), phone1 + " " + phone2 + " " + phone3 + " " + phone4 + " " + phone5, Toast.LENGTH_SHORT).show();

        gps = new GPSTracker(MainActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                    + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        String msgtxt = "I am in trouble. Please help.\n Location:" + "http://maps.google.co.in/maps?q=" + latitude + "," + longitude;
        SmsManager smsManager = SmsManager.getDefault();
        if (phone1.equals("")) {
            next();
        } else {
            smsManager.sendTextMessage(phone1, null, msgtxt, null, null);
            i++;
        }
        if (phone2.equals("")) {
            next();
        } else {
            smsManager.sendTextMessage(phone2, null, msgtxt, null, null);
            i++;
        }
        if (phone3.equals("")) {
            next();
        } else {
            smsManager.sendTextMessage(phone3, null, msgtxt, null, null);
            i++;
        }
        if (phone4.equals("")) {
            next();
        } else {
            smsManager.sendTextMessage(phone4, null, msgtxt, null, null);
            i++;
        }
        if (phone5.equals("")) {
            next();
        } else {
            smsManager.sendTextMessage(phone5, null, msgtxt, null, null);
            i++;
        }
    }

    public void next(){
        Toast.makeText(MainActivity.this,i+" Messages Sent and "+ (5-i)+" failed",Toast.LENGTH_LONG).show();
    }



}
        /*{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please enter your Fifth friend number");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(MainActivity.this,"Enter five phone number.",Toast.LENGTH_LONG).show();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }*/
