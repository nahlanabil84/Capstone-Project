package com.nanodegree.nahla.capstoneproject.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

public class TasksRemoteViewsService extends RemoteViewsService {

    public static void updateWidget(Context context, ArrayList<Task> tasks) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, TasksWidget.class));
        TasksWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        return new TasksRemoteViewsFactory(this.getApplicationContext());
    }
}
