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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.adapters.SubTaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.titleET)
    EditText titleET;
    @BindView(R.id.typeS)
    Spinner typeS;
    @BindView(R.id.addTypeIV)
    ImageView addTypeIV;
    @BindView(R.id.dateET)
    EditText dateET;
    @BindView(R.id.timeET)
    EditText timeET;
    @BindView(R.id.subTaskRV)
    RecyclerView subTaskRV;
    @BindView(R.id.addMediaLL)
    LinearLayout addMediaLL;
    @BindView(R.id.mediaRV)
    RecyclerView mediaRV;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.subTasksET)
    EditText subTasksET;
    @BindView(R.id.addSubTaskIV)
    ImageView addSubTaskIV;

    private SubTaskRVAdapter adapter;
    private Task task;
    private LinearLayoutManager layoutManager;
    ArrayList<String> subTasks;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AddTaskActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task = new Task();
        subTasks = new ArrayList<>();

        ButterKnife.bind(this);

        setToolbar();
        setRVAdapter();
        setSpinnerAdapter();
    }

    private void setSpinnerAdapter() {

    }

    private void setRVAdapter() {
        adapter = new SubTaskRVAdapter(subTasks);
        layoutManager = new LinearLayoutManager(this);
        subTaskRV.setAdapter(adapter);
        subTaskRV.setLayoutManager(layoutManager);
    }

    private void setUpDetails() {

        if (titleET.getText().toString() != null)
            task.setTaskTitle(titleET.getText().toString());
        else
            titleET.setError("error");

        if (timeET.getText().toString() != null)
            task.setTaskTime(timeET.getText().toString());
        else
            timeET.setError("error");

        if (dateET.getText().toString() != null)
            task.setTaskDate(dateET.getText().toString());
        else
            dateET.setError("error");

        if (seekBar != null)
            task.setTaskPriority(seekBar.getProgress());

        if (subTasks != null && subTasks.size() > 0)
            task.setSubTasks(subTasks);

    }

    private void setToolbar() {
        setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @OnClick(R.id.addTypeIV)
    public void onAddTypeIVClicked() {
        //ToDo Show Some Dialog to Add new Type
    }

    @OnClick(R.id.dateET)
    public void onDateETClicked() {
        //ToDo Show Some Dialog to Choose Date
    }

    @OnClick(R.id.timeET)
    public void onTimeETClicked() {
        //ToDo Show Some Dialog to Choose Time
    }

    @OnClick(R.id.addMediaLL)
    public void onAddMediaLLClicked() {
        //ToDo Show Some Dialog to Choose
    }

    @OnClick(R.id.addTaskB)
    public void onAddTaskBClicked() {
        setUpDetails();
    }

    @OnClick(R.id.addSubTaskIV)
    public void onAddSubTaskIVClicked() {
        if (!subTasksET.getText().toString().isEmpty()) {
            subTaskRV.setVisibility(View.VISIBLE);
            subTasks.add(subTasksET.getText().toString());
            adapter.notifyDataSetChanged();
            subTasksET.setText("");
        }
    }
}
