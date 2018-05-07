package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TypesFragment extends Fragment {

    @BindView(R.id.typesRV)
    RecyclerView typesRV;
    Unbinder unbinder;
    private TextView titleTV;
    private ImageView sortIV;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_types, container, false);

        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.types));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.GONE);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
