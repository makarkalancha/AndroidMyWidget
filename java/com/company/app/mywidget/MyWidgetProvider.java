package com.company.app.mywidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by makar on 29/09/2014.
 */
public class MyWidgetProvider extends AppWidgetProvider {
    private static final String LOG_TAG = "ExampleWidget";
    private static final String ACTION_CLICK = "ACTION_CLICK";



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //Get all ids
// TODO test with allWidgetIds
//        ComponentName thisWidget = new ComponentName(context, MyWidgetProvider.class);
//        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
//        for (int widget : allWidgetIds) {
        for (int widgetId : appWidgetIds) {
            int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Log.w(LOG_TAG, String.valueOf(number));
            //set the text
            remoteViews.setTextViewText(R.id.update, String.valueOf(number));

            //Register an onClickListener
            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(appWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        super.onReceive(context, intent);
//        if(CLOCK_WIDGET_UPDATE.equals(intent.getAction())) {
//            Log.d(LOG_TAG, "Clock update");
//            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), getClass().getName());
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            int[] ids = appWidgetManager.getAppWidgetIds(thisAppWidget);
//            for(int appWidgetID : ids) {
//                updateAppWidget(context, appWidgetManager, appWidgetID);
//            }
//        }
//    }
//
//    private PendingIntent createClockTickIntent(Context context) {
//        Intent intent = new Intent(CLOCK_WIDGET_UPDATE);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        return pendingIntent;
//    }
//
//    @Override
//    public void onDisabled(Context context) {
//        super.onDisabled(context);
//        Log.d(LOG_TAG, "Widget Provider disabled. Turning off timer");
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//    }
}
