package com.nanodegree.nahla.capstoneproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.adapters.QuickTaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMultiTasksActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.taskRV)
    RecyclerView taskRV;
    @BindView(R.id.addMoreLL)
    LinearLayout addMoreLL;
    @BindView(R.id.addAllB)
    Button addAllB;
    private QuickTaskRVAdapter adapter;
    private ArrayList<Task> tasks;
    private LinearLayoutManager layoutManager;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AddMultiTasksActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_multi_tasks);

        ButterKnife.bind(this);
        tasks = new ArrayList<>();
        setToolbar();
        setRVAdapter();
    }

    private void setRVAdapter() {
        adapter = new QuickTaskRVAdapter(tasks);
        layoutManager = new LinearLayoutManager(this);
        taskRV.setAdapter(adapter);
        taskRV.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(this);
    }

    @OnClick(R.id.addMoreLL)
    public void onAddMoreLLClicked() {
        tasks.add(new Task());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.addAllB)
    public void onAddAllBClicked() {
        //ToDo add list of tasks to DB
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
