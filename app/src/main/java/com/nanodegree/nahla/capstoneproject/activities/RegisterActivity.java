package com.nanodegree.nahla.capstoneproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.nanodegree.nahla.capstoneproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.profileCIV)
    CircleImageView profileCIV;
    @BindView(R.id.addPhotoIV)
    ImageView addPhotoIV;
    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.confirmPasswordET)
    EditText confirmPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.addPhotoIV)
    public void onAddPhotoIVClicked() {
    }

    @OnClick(R.id.registerB)
    public void onRegisterBClicked() {
    }
}
