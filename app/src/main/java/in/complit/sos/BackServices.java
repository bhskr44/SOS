package in.complit.sos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Bhaskar on 8/7/2017.
 */

public class BackServices  extends BroadcastReceiver {

    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, UpdateService.class));
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
        }
                Intent i = new Intent(context, UpdateService.class);
                i.putExtra("screen_state", screenOff);
                context.startService(i);
            }

}