package com.nanodegree.nahla.capstoneproject.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.adapters.SubTaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TASK_KEY = "TASK_KEY";
    Task task;
    @BindView(R.id.titleTV)
    TextView taskTitleTV;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.typeTV)
    TextView typeTV;
    @BindView(R.id.priorityTV)
    TextView priorityTV;
    @BindView(R.id.dateTV)
    TextView dateTV;
    @BindView(R.id.timeTV)
    TextView timeTV;
    @BindView(R.id.locationTV)
    TextView locationTV;
    @BindView(R.id.mediaTV)
    TextView mediaTV;
    @BindView(R.id.phoneNoTV)
    TextView phoneNoTV;
    @BindView(R.id.noteTV)
    TextView noteTV;
    @BindView(R.id.noteContainerLL)
    LinearLayout noteContainerLL;
    @BindView(R.id.subTasksRV)
    RecyclerView subTasksRV;
    @BindView(R.id.locationContainerLL)
    LinearLayout locationContainerLL;
    @BindView(R.id.mediaContainerLL)
    LinearLayout mediaContainerLL;
    @BindView(R.id.phoneContainerLL)
    LinearLayout phoneContainerLL;
    @BindView(R.id.subTasksContainerLL)
    LinearLayout subTasksContainerLL;
    @BindView(R.id.containerLL)
    LinearLayout containerLL;

    SubTaskRVAdapter adapter;
    LinearLayoutManager layoutManager;

    private Snackbar snackbar;

    public static Intent newInstance(Context context, Task task) {
        Intent intent = new Intent(context, TaskDetailsActivity.class);
        intent.putExtra(TASK_KEY, task);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        ButterKnife.bind(this);

        setToolbar();

        if (getIntent() != null && getIntent().getParcelableExtra(TASK_KEY) != null)
            setUpDetails(task = getIntent().getParcelableExtra(TASK_KEY));

    }

    private void setRVAdapter() {
        adapter = new SubTaskRVAdapter(task.getSubTasks());
        layoutManager = new LinearLayoutManager(this);
        subTasksRV.setAdapter(adapter);
        subTasksRV.setLayoutManager(layoutManager);
    }

    private void setToolbar() {
        setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(this);
    }

    private void setUpDetails(Task task) {
        taskTitleTV.setText(task.getTaskTitle());

        if (task.getTaskType() != null)
            typeTV.setText(task.getTaskType());
        else
            typeTV.setText(getString(R.string.unspecified));

        priorityTV.setText(task.getTaskPriority() + "");
        dateTV.setText(task.getTaskDate());
        timeTV.setText(task.getTaskTime());

        if (task.isLocationSet()) {
            locationContainerLL.setVisibility(View.VISIBLE);
            locationTV.setText(getAddressFromLocation(task.getTaskLocationLat(), task.getTaskLocationLng()));
        } else
            locationContainerLL.setVisibility(View.GONE);

        if (task.getTaskPhone() != null) {
            phoneContainerLL.setVisibility(View.VISIBLE);
            phoneNoTV.setText(task.getTaskPhone());
        } else
            phoneContainerLL.setVisibility(View.GONE);

        if (task.getTaskNote() != null) {
            noteContainerLL.setVisibility(View.VISIBLE);
            noteTV.setText(task.getTaskNote());
        } else
            noteContainerLL.setVisibility(View.GONE);

        mediaContainerLL.setVisibility(View.GONE);

        setRVAdapter();
    }

    @OnClick({R.id.locationTV, R.id.phoneNoTV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.locationTV:
                snackbar = Snackbar
                        .make(containerLL, getAddressFromLocation(task.getTaskLocationLat(), task.getTaskLocationLng()), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.visit), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //ToDo Open Map Zoomed on Location
                            }
                        });
                snackbar.show();

                break;
            case R.id.phoneNoTV:
                snackbar = Snackbar
                        .make(containerLL, task.getTaskPhone(), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.call), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //ToDo Make some call
                            }
                        });
                snackbar.show();

                break;
        }
    }

    private String getAddressFromLocation(float lat, float lng) {
        return "Address";
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
