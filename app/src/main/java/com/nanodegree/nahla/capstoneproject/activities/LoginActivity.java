package com.nanodegree.nahla.capstoneproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.nanodegree.nahla.capstoneproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.loginB)
    public void onLoginBClicked() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.registerTV)
    public void onRegisterTVClicked() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
