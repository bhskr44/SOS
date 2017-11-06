package in.complit.sos;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Bhaskar on 8/7/2017.
 */

public class UpdateService extends Service {

    DB myDBhelper = new DB(getApplicationContext());
    String phone1, phone2, phone3, phone4, phone5;
    GPSTracker gps;
    double longitude, latitude;
    int i;

    @Override
    public void onCreate() {
        super.onCreate();
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new BackServices();
        registerReceiver(mReceiver, filter);


    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
          /*  Cursor c = myDBhelper.getdata1();

            while (c.moveToNext()) {

                phone1 = c.getString(c.getColumnIndex(DB.col1));
                phone2 = c.getString(c.getColumnIndex(DB.col2));
                phone3 = c.getString(c.getColumnIndex(DB.col3));
                phone4 = c.getString(c.getColumnIndex(DB.col4));
                phone5 = c.getString(c.getColumnIndex(DB.col5));
            }
            Toast.makeText(getApplicationContext(), phone1 + " " + phone2 + " " + phone3 + " " + phone4 + " " + phone5, Toast.LENGTH_SHORT).show();

            gps = new GPSTracker(this);

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
*/
     //       Toast.makeText(getApplicationContext(), "I am off", Toast.LENGTH_SHORT).show();
        } else {
       //     messge();
            MainActivity.test(View );
            Toast.makeText(getApplicationContext(), "I am on", Toast.LENGTH_SHORT).show();
        }
    }
public void next(){
    Toast.makeText(getApplicationContext(),"I am",Toast.LENGTH_SHORT).show();

}
public void messge(){
    Cursor c = myDBhelper.getdata1();

    while (c.moveToNext()) {

        phone1 = c.getString(c.getColumnIndex(DB.col1));
        phone2 = c.getString(c.getColumnIndex(DB.col2));
        phone3 = c.getString(c.getColumnIndex(DB.col3));
        phone4 = c.getString(c.getColumnIndex(DB.col4));
        phone5 = c.getString(c.getColumnIndex(DB.col5));
    }
    Toast.makeText(getApplicationContext(), phone1 + " " + phone2 + " " + phone3 + " " + phone4 + " " + phone5, Toast.LENGTH_SHORT).show();

    gps = new GPSTracker(this);

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
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}