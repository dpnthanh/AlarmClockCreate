package com.dpn.thanh.alarmclockcreate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by thanhdpn on 27/10/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Dậy đi ba", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(context, MusicService.class);
        context.startService(intent1);

        Intent intent2 = new Intent(context, MainActivity.class);
        context.startActivity(intent2);
    }
}
