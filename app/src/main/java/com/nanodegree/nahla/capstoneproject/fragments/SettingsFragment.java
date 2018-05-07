package com.nanodegree.nahla.capstoneproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.nahla.capstoneproject.R;

public class SettingsFragment extends Fragment {

    private TextView titleTV;
    private ImageView sortIV;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        titleTV = getActivity().findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.settings));

        sortIV = getActivity().findViewById(R.id.sortIV);
        sortIV.setVisibility(View.GONE);

        return view;
    }

}
