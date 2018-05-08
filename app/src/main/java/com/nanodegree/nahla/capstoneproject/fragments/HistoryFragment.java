package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.adapters.TaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.historyRV)
    RecyclerView historyRV;
    private TextView titleTV;
    private ImageView sortIV;
    private ArrayList<Task> tasks;
    private TaskRVAdapter taskAdapter;
    private LinearLayoutManager layoutManager;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        unbinder = ButterKnife.bind(this, view);
        setToolbar();
        setUpRV();

        for (int i = 0; i < 3; i++)
            testTask(String.format("Task %d", i));

        return view;
    }

    private void setToolbar() {
        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.history));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.GONE);
    }

    private void setUpRV() {
        tasks = new ArrayList<>();
        taskAdapter = new TaskRVAdapter(tasks);
        layoutManager = new LinearLayoutManager(getContext());
        historyRV.setLayoutManager(layoutManager);
        historyRV.setAdapter(taskAdapter);
    }

    private void testTask(String format) {
        ArrayList<String> subTasks = new ArrayList<>();
        subTasks.add("asdfghjkl zxcvbnmx");
        subTasks.add("7890");
        subTasks.add("awxyz");
        subTasks.add("a1b2c3d4e5f6g7h8i9j0");
        subTasks.add("7890");
        subTasks.add("awxyz");
        subTasks.add("a1b2c3d4e5f6g7h8i9j0");

        Task task = new Task();
        task.setDone(true);
        task.setTaskTitle(format);
        task.setTaskType("normal");
        task.setTaskPriority(5);
        task.setTaskDate("13 - 5 - 2018");
        task.setTaskTime("11:45 AM");
        task.setLocationSet(false);
        task.setTaskNote("Meet at my place by 12:15 PM");
        task.setTaskPhone("400");
        task.setSubTasks(subTasks);

        tasks.add(task);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
