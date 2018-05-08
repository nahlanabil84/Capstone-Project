package com.nanodegree.nahla.capstoneproject.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;


public class TasksRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    ArrayList<Task> tasks;

    public TasksRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        tasks = new ArrayList<>();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.item_widget_task);

        remoteView.setTextViewText(R.id.taskTV, tasks.get(position).getTaskTitle());
        remoteView.setTextViewText(R.id.timeTV, tasks.get(position).getTaskTime());

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
