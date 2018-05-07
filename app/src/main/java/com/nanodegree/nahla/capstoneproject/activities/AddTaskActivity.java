package com.nanodegree.nahla.capstoneproject.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.adapters.SubTaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

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

    ArrayAdapter<String> typesAA;
    ArrayList<String> types;
    String type;

    private Calendar mCalender;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

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
        types = new ArrayList<>();
        types.add("UnSpecified");
        types.add("Meeting");
        types.add("Call");
        types.add("Study");


        ButterKnife.bind(this);

        setToolbar();
        setRVAdapter();
        setSpinnerAdapter();
        setDateDialog();
        setTimeDialog();
    }

    private void setDateDialog() {
        mCalender = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (mCalender == null)
                    mCalender = Calendar.getInstance();

                mCalender.set(year, month, dayOfMonth, 0, 0);

                int m = month + 1;
                dateET.setText(new StringBuilder().append(dayOfMonth)
                        .append("-").append(m).append("-").append(year)
                        .append(" "));

            }
        }, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH));
    }

    private void setTimeDialog() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String amOrPm;

                if (hourOfDay >= 12) {
                    amOrPm = "PM";
                    hourOfDay -= 12;
                } else
                    amOrPm = "AM";

                if (hourOfDay == 0)
                    hourOfDay = 12;

                timeET.setText(String.format("%02d:%02d %s", hourOfDay, minute, amOrPm));
            }
        }, 12, 0, false);
    }

    private void setSpinnerAdapter() {
        typesAA = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        typesAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeS.setAdapter(typesAA);
        typeS.setOnItemSelectedListener(this);
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
            titleET.setError(getString(R.string.empty_));

        if (timeET.getText().toString() != null)
            task.setTaskTime(timeET.getText().toString());
        else
            timeET.setError(getString(R.string.empty_));

        if (dateET.getText().toString() != null)
            task.setTaskDate(dateET.getText().toString());
        else
            dateET.setError(getString(R.string.empty_));

        if (seekBar != null)
            task.setTaskPriority(seekBar.getProgress());

        if (subTasks != null && subTasks.size() > 0)
            task.setSubTasks(subTasks);

        if (type != null)
            task.setTaskType(type);

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
        showAddTypeDialog();
    }

    private void showAddTypeDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getString(R.string.add_type));

        final EditText input = new EditText(AddTaskActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(32, 0, 32, 0);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getString(R.string.add_type), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!input.getText().toString().isEmpty())
                    types.add(input.getText().toString());

                setSpinnerAdapter();
                dialog.cancel();
            }
        });

        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.dateET)
    public void onDateETClicked() {
        if (datePickerDialog.isShowing())
            datePickerDialog.hide();

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @OnClick(R.id.timeET)
    public void onTimeETClicked() {
        timePickerDialog.show();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
