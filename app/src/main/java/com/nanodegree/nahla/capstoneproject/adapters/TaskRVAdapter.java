package com.nanodegree.nahla.capstoneproject.adapters;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.activities.TaskDetailsActivity;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskRVAdapter extends RecyclerView.Adapter<TaskRVAdapter.TaskViewHolder> {
    View itemView;
    ArrayList<Task> tasks;

    public TaskRVAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_today_task, parent, false);

        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position) {
        holder.emptyVH();

        holder.taskTV.setText(tasks.get(position).getTaskTitle());
        holder.dayTV.setText(tasks.get(position).getTaskDate());
        holder.timeTV.setText(tasks.get(position).getTaskTime());

        if (tasks.get(position).isDone())
            holder.taskCB.setChecked(true);

        holder.taskCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                if (!tasks.get(position).isDone()) {

                    if (isChecked) {
                        buttonView.setChecked(true);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttonView.setChecked(false);
                                tasks.remove(position);
                                notifyDataSetChanged();
                            }
                        }, 1000);

                    }
                } else
                    buttonView.setChecked(!isChecked);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.getContext().startActivity(TaskDetailsActivity.newInstance(holder.itemView.getContext(), tasks.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.taskCB)
        public CheckBox taskCB;
        @BindView(R.id.taskTV)
        public TextView taskTV;
        @BindView(R.id.dayTV)
        public TextView dayTV;
        @BindView(R.id.timeTV)
        public TextView timeTV;

        public TaskViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void emptyVH() {
            taskCB.setText("");
            taskCB.setChecked(false);

            taskTV.setText("");
            dayTV.setText("");
            timeTV.setText("");

        }
    }

}
