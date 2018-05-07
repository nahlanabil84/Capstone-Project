package com.nanodegree.nahla.capstoneproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nanodegree.nahla.capstoneproject.R;

public class AddTypeActivity extends AppCompatActivity {

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AddTypeActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type);
    }
}
