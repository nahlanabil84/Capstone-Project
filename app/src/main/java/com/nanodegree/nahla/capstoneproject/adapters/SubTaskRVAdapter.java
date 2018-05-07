package com.nanodegree.nahla.capstoneproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.nanodegree.nahla.capstoneproject.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubTaskRVAdapter extends RecyclerView.Adapter<SubTaskRVAdapter.SubTaskViewHolder> {

    View itemView;
    ArrayList<String> subTasks;

    public SubTaskRVAdapter(ArrayList<String> subTasks) {
        this.subTasks = subTasks;
    }

    @NonNull
    @Override
    public SubTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub_task, parent, false);

        return new SubTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubTaskViewHolder holder, final int position) {
        holder.emptyVH();

        holder.subTaskCB.setText(subTasks.get(position));

        holder.subTaskCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setChecked(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    public class SubTaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subTaskCB)
        CheckBox subTaskCB;

        public SubTaskViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void emptyVH() {
            subTaskCB.setText("");
            subTaskCB.setChecked(false);
        }
    }

}
