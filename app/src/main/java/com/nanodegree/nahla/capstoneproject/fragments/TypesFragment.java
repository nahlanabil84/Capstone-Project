package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.Utils.SharedPref;
import com.nanodegree.nahla.capstoneproject.activities.AddTypeActivity;
import com.nanodegree.nahla.capstoneproject.adapters.TypeRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TABLE;
import static com.nanodegree.nahla.capstoneproject.Utils.Const.USERS_TYPES_TABLE;

public class TypesFragment extends Fragment {
    final String TAG = "Database Log";

    @BindView(R.id.typesRV)
    RecyclerView typesRV;
    @BindView(R.id.addTypeFAB)
    FloatingActionButton addTypeFAB;

    Unbinder unbinder;

    private TextView titleTV;
    private ImageView sortIV;
    private TypeRVAdapter adapter;
    private ArrayList<Type> types;
    private GridLayoutManager layoutManager;
    private DatabaseReference databaseRef;
    private int typesSize;


    public TypesFragment() {
        // Required empty public constructor
    }

    public static TypesFragment newTypesFInstance() {
        TypesFragment fragment = new TypesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_types, container, false);

        unbinder = ButterKnife.bind(this, view);

        databaseRef = FirebaseDatabase.getInstance()
                .getReference()
                .child(USERS_TABLE + "/" + new SharedPref(getContext()).getUserFbId() + "/" + USERS_TYPES_TABLE);

        setToolbar();
        setUpRV();
        readDatabaseTypes();

        return view;
    }

    private void readDatabaseTypes() {
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                types.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Type type = snapshot.getValue(Type.class);
                    type.setTypeId(snapshot.getKey());
                    types.add(type);
                    typesSize = Integer.parseInt(snapshot.getKey());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void setUpRV() {
        types = new ArrayList<>();
        adapter = new TypeRVAdapter(types);
        layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        typesRV.setLayoutManager(layoutManager);
        typesRV.setAdapter(adapter);
    }

    private void setToolbar() {
        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.types));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.addTypeFAB)
    public void onViewClicked() {
        getContext().startActivity(AddTypeActivity.newInstance(getContext(), typesSize));
    }
}
