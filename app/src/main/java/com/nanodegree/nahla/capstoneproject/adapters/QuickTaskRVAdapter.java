package com.nanodegree.nahla.capstoneproject.adapters;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuickTaskRVAdapter extends RecyclerView.Adapter<QuickTaskRVAdapter.QuickTaskViewHolder> {
    View itemView;
    ArrayList<Task> tasks;

    public QuickTaskRVAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public QuickTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quick_task, parent, false);

        return new QuickTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuickTaskViewHolder holder, final int position) {
        holder.emptyVH();

        if (tasks.get(position).getTaskTitle() != null)
            holder.titleET.setText(tasks.get(position).getTaskTitle());

        if (tasks.get(position).getTaskDate() != null)
            holder.dateET.setText(tasks.get(position).getTaskDate());

        if (tasks.get(position).getTaskTime() != null)
            holder.timeET.setText(tasks.get(position).getTaskTime());

        holder.dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateDialog(holder, holder.dateET);
            }
        });

        holder.timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimeDialog(holder, holder.timeET);
            }
        });

        holder.addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.remove(position);
                tasks.add(position, setUpTask(holder));
                notifyDataSetChanged();
            }
        });

        holder.cancelFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasks.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    private void setDateDialog(QuickTaskViewHolder holder, final EditText dateET) {
        final Calendar mCalender = Calendar.getInstance();
        new DatePickerDialog(holder.itemView.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalender.set(year, month, dayOfMonth, 0, 0);

                int m = month + 1;
                dateET.setText(new StringBuilder().append(dayOfMonth)
                        .append("-").append(m).append("-").append(year)
                        .append(" "));
            }
        }, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setTimeDialog(QuickTaskViewHolder holder, final EditText timeET) {
        new TimePickerDialog(holder.itemView.getContext(), new TimePickerDialog.OnTimeSetListener() {
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
        }, 12, 0, false).show();
    }

    private Task setUpTask(@NonNull QuickTaskViewHolder holder) {
        Task task = new Task();

        if (!holder.titleET.getText().toString().isEmpty())
            task.setTaskTitle(holder.titleET.getText().toString());
        else
            holder.titleET.setError(holder.itemView.getContext().getString(R.string.empty_));

        if (!holder.timeET.getText().toString().isEmpty())
            task.setTaskTime(holder.timeET.getText().toString());

        if (!holder.dateET.getText().toString().isEmpty())
            task.setTaskDate(holder.dateET.getText().toString());

        return task;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class QuickTaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleET)
        EditText titleET;
        @BindView(R.id.dateET)
        EditText dateET;
        @BindView(R.id.timeET)
        EditText timeET;
        @BindView(R.id.addFAB)
        FloatingActionButton addFAB;
        @BindView(R.id.cancelFAB)
        FloatingActionButton cancelFAB;

        public QuickTaskViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void emptyVH() {
            titleET.setText("");
            dateET.setText("");
            timeET.setText("");
        }
    }
}
