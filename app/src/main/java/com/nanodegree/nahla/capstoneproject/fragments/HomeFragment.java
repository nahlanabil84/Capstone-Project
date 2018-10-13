package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.Utils.SharedPref;
import com.nanodegree.nahla.capstoneproject.activities.AddMultiTasksActivity;
import com.nanodegree.nahla.capstoneproject.activities.AddTaskActivity;
import com.nanodegree.nahla.capstoneproject.activities.AddTypeActivity;
import com.nanodegree.nahla.capstoneproject.adapters.TaskRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TABLE;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TYPES_TABLE;

public class HomeFragment extends Fragment implements View.OnClickListener {

    final String TAG = "Database Log";

    TextView titleTV;
    ImageView sortIV;
    @BindView(R.id.taskRV)
    RecyclerView taskRV;

    Unbinder unbinder;

    ArrayList<Task> tasks;
    TaskRVAdapter taskAdapter;
    LinearLayoutManager layoutManager;

    int typesSize = 0;
    private DatabaseReference databaseRef;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newHomeFInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);
        databaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS_TABLE + "/" + new SharedPref(getContext()).getUserFbId() + "/" + USERS_TYPES_TABLE);
        readDatabaseTypes();

        setUpToolbar();
        setUpRV();
        for (int i = 0; i < 5; i++)
            testTask(String.format("Task %d", i));

        return view;
    }

    private void readDatabaseTypes() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    typesSize = Integer.parseInt(snapshot.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setUpRV() {
        tasks = new ArrayList<>();
        taskAdapter = new TaskRVAdapter(tasks);
        layoutManager = new LinearLayoutManager(getContext());
        taskRV.setLayoutManager(layoutManager);
        taskRV.setAdapter(taskAdapter);
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
        task.setDone(false);
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

    private void setUpToolbar() {
        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.today_task));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.VISIBLE);
        sortIV.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sortIV: {
                Toast.makeText(getContext(), getString(R.string.sort_by_name), Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    @OnClick({R.id.typeFAB, R.id.listFAB, R.id.taskFAB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.typeFAB:
                getContext().startActivity(AddTypeActivity.newInstance(getContext(), typesSize));
                break;
            case R.id.listFAB:
                getContext().startActivity(AddMultiTasksActivity.newInstance(getContext()));
                break;
            case R.id.taskFAB:
                getContext().startActivity(AddTaskActivity.newInstance(getContext()));
                break;
        }
    }
}
