package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;
import com.nanodegree.nahla.capstoneproject.activities.AddTypeActivity;
import com.nanodegree.nahla.capstoneproject.adapters.TypeRVAdapter;
import com.nanodegree.nahla.capstoneproject.models.Type;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TypesFragment extends Fragment {

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


    public TypesFragment() {
        // Required empty public constructor
    }

    public static TypesFragment newInstance() {
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

        setToolbar();
        setUpRV();

        for (int i = 0; i < 5; i++)
            testType(String.format("Type %d", i));

        return view;
    }

    private void testType(String format) {
        Type type = new Type();
        type.setTypeTitle(format);
        type.setTypeColor("#56f747");

        types.add(type);
        adapter.notifyDataSetChanged();
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
        getContext().startActivity(AddTypeActivity.newInstance(getContext()));
    }
}
